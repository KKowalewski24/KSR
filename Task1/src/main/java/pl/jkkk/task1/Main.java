package pl.jkkk.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import pl.jkkk.task1.exception.MetricNotSupportedException;
import pl.jkkk.task1.featureextraction.FeatureExtractorDecorator;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.KeywordsExtractor;
import pl.jkkk.task1.featureextraction.Metric;
import pl.jkkk.task1.featureextraction.DocumentLengthFE;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.RelativeNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.UniqueNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.knn.KnnAlgorithm;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;
import static pl.jkkk.task1.constant.Constants.CHEBYSHEV_ABBREVIATION;
import static pl.jkkk.task1.constant.Constants.CHOSEN_PLACES;
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
    private static List<Set<String>> keywordsSets;
    private static List<FeatureVector> trainingFeatureVectors;
    private static List<FeatureVector> testFeatureVectors;

    private static Map<String, Integer> properlyClassified;
    private static Map<String, Integer> classified;
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
        if (args.length != 4) {
            System.out.println("Wrong parameters");
            System.out.println(
                    "<Percentage of training set> <k for kNN> <metric - eucl or manh or cheb> <number of keywords>");

            System.exit(0);
        } else {
            int percentageOfTrainingSet = Integer.valueOf(args[0]);
            int numberK = Integer.valueOf(args[1]);
            String metricAbbr = args[2];
            int numberOfKeywords = Integer.valueOf(args[3]);

            if ((percentageOfTrainingSet < 1 || percentageOfTrainingSet > 99)
                    || numberK < 1
                    || !(metricAbbr.equals(EUCLIDEAN_ABBREVIATION)
                    || metricAbbr.equals(MANHATTAN_ABBREVIATION)
                    || metricAbbr.equals(CHEBYSHEV_ABBREVIATION))) {

                System.err.println("Wrong parameters");
                System.out.println(
                        "<Percentage of training set> <k for kNN> <metric - eucl or manh or cheb>");

                System.exit(1);
            }

            /*----- DOCUMENTS PREPARATION -----*/
            readDocuments();
            filterDocuments();
            stemDocuments();
            removeStopWords();

            /*-----  -----*/
            calculateWordOccurrences();
            retrieveKeywords(numberOfKeywords);
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
                        .filter((it) -> it.getPlaceList().size() == 1
                                && CHOSEN_PLACES.contains(it.getPlaceList().get(0)))
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

    private static void retrieveKeywords(int numberOfKeywords) {
        keywordsSets = new ArrayList<>();
        action(() -> {
            keywordsSets.add(keywordsExtractor.getKeywordsByClassTF(numberOfKeywords));
            keywordsSets.add(keywordsExtractor.getKeywordsByClassTFIDF(numberOfKeywords));
            keywordsSets.add(keywordsExtractor.getKeywordsByTFIDF(numberOfKeywords));
        }, "Retrieving keywords");
    }

    private static void divideIntoTwoSets(int divisionRatio) {
        Random random = new Random();
        final int testSetSize = documents.size() * (100 - divisionRatio) / 100;

        action(() -> {
            /*----- MAKE A COPY OF DOCUMENTS -----*/
            testDocuments = new ArrayList<>();
            trainingDocuments = documents
                    .stream()
                    .collect(Collectors.toCollection(ArrayList::new));

            /*----- MOVE RANDOM ITEMS TO TEST LIST -----*/
            for (int i = 0; i < testSetSize; i++) {
                int number = random.nextInt(trainingDocuments.size());
                testDocuments.add(trainingDocuments.get(number));
                trainingDocuments.remove(number);
            }
        }, "Dividing into two lists");
    }

    private static void extractFeatures() {
        trainingFeatureVectors = new ArrayList<>();
        testFeatureVectors = new ArrayList<>();

        extractorDecorator = new FeatureExtractorDecorator();
        extractorDecorator.addExtractor(new DocumentLengthFE());
        keywordsSets.forEach(keywords -> {
            extractorDecorator.addExtractor(new UniqueNumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
            extractorDecorator.addExtractor(new UniqueNumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
            extractorDecorator.addExtractor(new NumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
            extractorDecorator.addExtractor(new NumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
            extractorDecorator.addExtractor(new RelativeNumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
            extractorDecorator.addExtractor(new RelativeNumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
        });

        action(() -> {
            trainingFeatureVectors.addAll(trainingDocuments.stream()
                    .map(extractorDecorator::extract)
                    .collect(Collectors.toList()));

            testFeatureVectors.addAll(testDocuments.stream()
                    .map(extractorDecorator::extract)
                    .collect(Collectors.toList()));
        }, "Features extracting");

        action(() -> {
            FeatureVector.normalize(trainingFeatureVectors);
            FeatureVector.normalize(testFeatureVectors);
        }, "Normalize feature vectors");
    }

    private static void knnClassification(int numberK, String metricAbbreviation) {
        action(() -> {
            properlyClassified = new HashMap<>();
            classified = new HashMap<>();
            for (FeatureVector it : testFeatureVectors) {
                try {
                    String properPlace = it.getDocument().getPlaceList().get(0);
                    if (properPlace.equals(knnAlgorithm
                            .calculateAndClassify(it, trainingFeatureVectors, numberK,
                                    Metric.convertAbbreviationToMetric(metricAbbreviation)))) {
                        properlyClassified.put(properPlace, Optional.ofNullable(properlyClassified.get(properPlace)).orElse(0) + 1);
                    }
                    classified.put(properPlace, Optional.ofNullable(classified.get(properPlace)).orElse(0) + 1);
                } catch (MetricNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }

        }, "kNN");
    }

    private static void printStatistics() {
        System.out.println("\n-------------------------------------\n");
        classified.keySet().forEach(place -> {
            System.out.println(place + ": " + (Optional.ofNullable(properlyClassified.get(place)).orElse(0) * 100.0 / classified.get(place)) + "%");
        });
        System.out.println();
        System.out.println("All: " + (properlyClassified.values().stream().mapToInt(value -> value).sum() * 100.0 /
                classified.values().stream().mapToInt(value -> value).sum()) + "%");
        System.out.println("Overall Time: " + overallTime + "s");
        System.out.println("\n-------------------------------------\n");
    }
}
