package pl.jkkk.task1;

import pl.jkkk.task1.exception.MetricNotSupportedException;
import pl.jkkk.task1.featureextraction.FeatureExtractorDecorator;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.KeywordsExtractor;
import pl.jkkk.task1.featureextraction.Metric;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsFE;
import pl.jkkk.task1.featureextraction.RelativeNumberOfKeywordsFE;
import pl.jkkk.task1.knn.KnnAlgorithm;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.jkkk.task1.constant.Constants.CHEBYSHEV_ABBREVIATION;
import static pl.jkkk.task1.constant.Constants.EUCLIDEAN_ABBREVIATION;
import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;
import static pl.jkkk.task1.constant.Constants.MANHATTAN_ABBREVIATION;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static SgmlFileReader sgmlFileReader = new SgmlFileReader();
    private static DocumentStemmer documentStemmer = new DocumentStemmer();
    private static WordRemover wordRemover = new WordRemover();
    private static KeywordsExtractor keywordsExtractor;
    private static FeatureExtractorDecorator extractorDecorator;
    private static KnnAlgorithm knnAlgorithm = new KnnAlgorithm();

    private static List<Document> documents;
    private static List<Document> trainingDocuments;
    private static List<Document> testDocuments;
    private static Set<String> keywords;
    private static List<FeatureVector> trainingFeatureVectors;
    private static List<FeatureVector> testFeatureVectors;

    private static double classificationEffectiveness = 0;
    private static double overallTime = 0;

    /*
     * Call parameters
     *
     * 1. Percentage of training to test ratio
     * args[0] = 60 then trainingSet.size():= documents.size()*60/100
     *
     * 2. Chosen K for kNN
     *
     * 3. Chosen metric - use abbreviation
     * Euclidean - eucl
     * Manhattan - manh
     * Chebyshev - cheb
     *
     * */

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) {
        if (args.length != 3) {
            System.exit(0);
        } else {
            int percentageOfTrainingSet = Integer.valueOf(args[0]);
            int numberK = Integer.valueOf(args[1]);
            String metricAbbr = args[2];

            if ((percentageOfTrainingSet < 1 || percentageOfTrainingSet > 99)
                    || numberK < 1
                    || !(metricAbbr.equals(EUCLIDEAN_ABBREVIATION)
                    || metricAbbr.equals(MANHATTAN_ABBREVIATION)
                    || metricAbbr.equals(CHEBYSHEV_ABBREVIATION))) {

                System.err.println("Wrong parameters");
                System.exit(-1);
            }

            /*----- DOCUMENTS PREPARATION -----*/
            readDocuments();
            filterDocuments();
            stemDocuments();
            removeStopWords();

            /*-----  -----*/
            calculateWordOccurrences();
            retrieveKeywords();
            divideIntoTwoSets(percentageOfTrainingSet);
            extractFeatures();
            knnClassification(numberK, metricAbbr);

            /*----- SUMMARY -----*/
            printStatistics();
        }
    }

    private static void action(Runnable runnable, String description) {
        long startTime = System.currentTimeMillis();
        System.out.print(description + "... ");
        runnable.run();
        double durationTime = ((System.currentTimeMillis() - startTime) / 1000.0);
        overallTime += durationTime;
        System.out.println(durationTime + "s");
    }

    private static void readDocuments() {
        action(() -> {
            try {
                documents = sgmlFileReader.readFromFiles(FILENAME_LIST);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "Reading documents");
    }

    private static void filterDocuments() {
        action(() -> documents = documents
                        .stream()
                        .filter((it) -> it.getPlaceList().size() == 1)
                        .collect(Collectors.toCollection(ArrayList::new)),
                "Removing documents with multiple places");
    }

    private static void stemDocuments() {
        action(() -> documentStemmer.performStemmingProcessOnWordList(documents),
                "Stemming documents");
    }

    private static void removeStopWords() {
        action(() -> {
            try {
                wordRemover.removeStopWords(documents);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "Removing stop words");
    }

    private static void calculateWordOccurrences() {
        keywordsExtractor = new KeywordsExtractor(documents);
        action(() -> keywordsExtractor.calculateOccurrences(),
                "Calculating word occurrences");
    }

    private static void retrieveKeywords() {
        keywords = new HashSet<>();
        action(() -> keywords.addAll(keywordsExtractor.getKeywordsByTPSD(100)),
                "Retrieving keywords");
    }

    private static void divideIntoTwoSets(int divisionRatio) {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>();

        /*----- CALCULATE SIZES -----*/
        int trainingSetSize = documents.size() * divisionRatio / 100;
        int testSetSize = documents.size() - trainingSetSize;

        action(() -> {
            for (int i = 0; i < testSetSize; i++) {
                randomNumbers.add(random.nextInt(documents.size()));
            }

            /*----- MAKE A COPY OF DOCUMENTS -----*/
            testDocuments = new ArrayList<>();
            trainingDocuments = documents
                    .stream()
                    .collect(Collectors.toCollection(ArrayList::new));

            /*----- MOVE RANDOM ITEMS TO TEST LIST -----*/
            for (int i = 0; i < randomNumbers.size(); i++) {
                testDocuments.add(trainingDocuments.get(i));
                trainingDocuments.remove(i);
            }
        }, "Dividing into two lists");
    }

    private static void extractFeatures() {
        trainingFeatureVectors = new ArrayList<>();
        testFeatureVectors = new ArrayList<>();

        extractorDecorator = new FeatureExtractorDecorator();
        extractorDecorator.addExtractor(new NumberOfKeywordsFE(keywords));
        extractorDecorator.addExtractor(new RelativeNumberOfKeywordsFE(keywords));

        action(() -> {
            trainingFeatureVectors.addAll(trainingDocuments.stream()
                    .map(extractorDecorator::extract)
                    .collect(Collectors.toList()));

            testFeatureVectors.addAll(testDocuments.stream()
                    .map(extractorDecorator::extract)
                    .collect(Collectors.toList()));
        }, "Features extracting");
    }

    private static boolean checkPlaceEquality(FeatureVector featureVector, String classifiedPlace) {
        if (featureVector.getDocument().getPlaceList().get(0).equals(classifiedPlace)) {
            return true;
        }

        return false;
    }

    private static void knnClassification(int numberK, String metricAbbreviation) {
        action(() -> {
            int succeeded = 0;

            for (FeatureVector it : testFeatureVectors) {
                try {
                    if (checkPlaceEquality(it, knnAlgorithm
                            .calculateAndClassify(it, trainingFeatureVectors, numberK,
                                    Metric.convertAbbreviationToMetric(metricAbbreviation)))) {
                        succeeded++;
                    }
                } catch (MetricNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }

            classificationEffectiveness = (double) succeeded / (double) testFeatureVectors.size();
        }, "kNN");
    }

    private static void printStatistics() {
        System.out.println("\n-------------------------------------\n");
        System.out.println("Classification Effectiveness: "
                + classificationEffectiveness * 100 + "%");
        System.out.println("Overall Time: " + overallTime + "s");
        System.out.println("\n-------------------------------------\n");
    }
}
