package pl.jkkk.task1;

import pl.jkkk.task1.featureextraction.DocumentLengthFE;
import pl.jkkk.task1.featureextraction.FeatureExtractorDecorator;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.KeywordsExtractor;
import pl.jkkk.task1.featureextraction.MostFrequentKeywordInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.MostFrequentWordInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.NumericalMetric;
import pl.jkkk.task1.featureextraction.RelativeNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.TextMetric;
import pl.jkkk.task1.featureextraction.UniqueNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.knn.KnnAlgorithm;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.jkkk.task1.constant.Constants.CHOSEN_PLACES;
import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

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

    private static Map<String, Map<String, Integer>> classification;
    private static double overallTime = 0;

    private static void printUsage() {
        System.out.println("Required parameters:  \n"
                + "\t<percentage of training set (integer 1-99)>\n"
                + "\t<k for kNN (integer >0)>\n"
                + "\t<number of keywords (integer >0)>\n"
                + "\t<numerical metric [eucl|manh|cheb]>\n"
                + "\t<text metric [trigram|tfm]>");
        System.exit(0);
    }

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) {
        /*----- RETRIEVE PROGRAM PARAMS -----*/
        int percentageOfTrainingSet = 0;
        int numberK = 0;
        int numberOfKeywords = 0;
        NumericalMetric numericalMetric = null;
        TextMetric textMetric = null;
        try {
            percentageOfTrainingSet = Integer.valueOf(args[0]);
            numberK = Integer.valueOf(args[1]);
            numberOfKeywords = Integer.valueOf(args[2]);
            numericalMetric = NumericalMetric.fromString(args[3]);
            textMetric = TextMetric.fromString(args[4]);
        } catch (Exception e) {
            System.out.println(e);
            printUsage();
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
        knnClassification(numberK, numericalMetric, textMetric);

        /*----- SUMMARY -----*/
        printStatistics(args);
        saveGeneratedDataToFile(args);
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
        action(() -> documents = documents.stream()
                .filter((it) -> it.getPlaceList()
                        .size() == 1 && CHOSEN_PLACES.contains(it.getPlaceList().get(0)))
                .collect(Collectors.toCollection(ArrayList::new)), "Removing documents with "
                + "multiple places");
    }

    private static void stemDocuments() {
        action(() -> documentStemmer.performStemmingProcessOnWordList(documents), "Stemming "
                + "documents");
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
        action(() -> keywordsExtractor.calculateOccurrences(), "Calculating word occurrences");
    }

    private static void retrieveKeywords(int numberOfKeywords) {
        keywordsSets = new ArrayList<>();
        action(() -> {
            keywordsSets.add(keywordsExtractor.getKeywordsByClassTFIOCTF(numberOfKeywords));
            keywordsSets.add(keywordsExtractor.getKeywordsByTFIDF(numberOfKeywords));
        }, "Retrieving keywords");
    }

    private static void divideIntoTwoSets(int divisionRatio) {
        Random random = new Random(47);
        final int testSetSize = documents.size() * (100 - divisionRatio) / 100;

        action(() -> {
            /*----- MAKE A COPY OF DOCUMENTS -----*/
            testDocuments = new ArrayList<>();
            trainingDocuments = documents.stream().collect(Collectors.toCollection(ArrayList::new));

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
            extractorDecorator.addExtractor(new NumberOfKeywordsInDocumentFragmentFE(keywords, 0,
                    50));
            extractorDecorator.addExtractor(new NumberOfKeywordsInDocumentFragmentFE(keywords, 50
                    , 100));
            extractorDecorator.addExtractor(new RelativeNumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
            extractorDecorator.addExtractor(new RelativeNumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
            extractorDecorator.addExtractor(new MostFrequentKeywordInDocumentFragmentFE(keywords,
                    0, 50));
            extractorDecorator.addExtractor(new MostFrequentKeywordInDocumentFragmentFE(keywords,
                    50, 100));
        });
        extractorDecorator.addExtractor(new MostFrequentWordInDocumentFragmentFE(0, 50));
        extractorDecorator.addExtractor(new MostFrequentWordInDocumentFragmentFE(50, 100));

        action(() -> {
            trainingFeatureVectors
                    .addAll(trainingDocuments.stream()
                            .map(extractorDecorator::extract)
                            .collect(Collectors.toList()));

            testFeatureVectors
                    .addAll(testDocuments.stream()
                            .map(extractorDecorator::extract)
                            .collect(Collectors.toList()));
        }, "Features extracting");

        action(() -> {
            FeatureVector.normalize(trainingFeatureVectors);
            FeatureVector.normalize(testFeatureVectors);
        }, "Normalize feature vectors");
    }

    private static void knnClassification(int numberK, NumericalMetric numericalMetric,
                                          TextMetric textMetric) {
        action(() -> {
            classification = new HashMap<>();
            for (FeatureVector it : testFeatureVectors) {
                final String properPlace = it.getDocument().getPlaceList().get(0);
                final String recognizedPlace = knnAlgorithm
                        .calculateAndClassify(it, trainingFeatureVectors, numberK,
                                numericalMetric, textMetric);
                if (!classification.containsKey(properPlace)) {
                    classification.put(properPlace, new HashMap<>());
                }
                classification.get(properPlace)
                        .put(recognizedPlace, classification.get(properPlace)
                                .getOrDefault(recognizedPlace, 0) + 1);
            }

        }, "kNN");
    }

    private static String generateStatistics(String[] args, boolean printTable) {
        StringBuilder result = new StringBuilder();
        StringBuilder resultFile = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");

        final int classified = classification.values().stream()
                .mapToInt(classes -> classes.values().stream().mapToInt(x -> x).sum()).sum();
        final int properlyClassified = classification.keySet().stream().mapToInt(
                clazz -> classification.get(clazz)
                        .keySet()
                        .stream()
                        .filter(recognizedClass -> recognizedClass.equals(clazz))
                        .mapToInt(recognizedClass -> classification.get(clazz).get(recognizedClass))
                        .sum()).sum();
        double accuracy = properlyClassified / (double) classified;

        if (printTable) {
            int percentageOfTrainingSet = Integer.valueOf(args[0]);
            int numberK = Integer.valueOf(args[1]);
            int numberOfKeywords = Integer.valueOf(args[2]);
            NumericalMetric numericalMetric = NumericalMetric.fromString(args[3]);
            TextMetric textMetric = TextMetric.fromString(args[4]);

            resultFile.append("& ").append(percentageOfTrainingSet).append("\t\t\t\n")
                    .append("& ").append(numberK).append("\t\t\t\n")
                    .append("& ").append(numberOfKeywords).append("\t\t\t\n")
                    .append("& ").append(numericalMetric).append("\t\t\t\n")
                    .append("& ").append(textMetric).append("\t\t\t\n")
                    .append("& ").append(df.format(accuracy * 100)).append("\t\t\t\n")
                    .append("& ").append(df.format(overallTime)).append("\t\t\t\n");
        }

        result.append("\n-------------------------------------\n\n");

        classification.forEach((clazz, classes) -> {
            final int classifiedToClazz = classification.values().stream()
                    .mapToInt(allClasses -> allClasses.getOrDefault(clazz, 0))
                    .sum();
            final int classifiedFromClazz = classes.values().stream().mapToInt(x -> x).sum();
            final int classifiedFromClazzToClazz = classes.getOrDefault(clazz, 0);
            final double recall = classifiedFromClazzToClazz / (double) classifiedFromClazz;
            final double precision = classifiedFromClazzToClazz / (double) classifiedToClazz;
            result.append(clazz + "   recall = " + recall + "   precision = " + precision + "\n");

            if (printTable) {
                resultFile.append("& ").append(df.format(recall)).append("\t\t\t\n");
                resultFile.append("& ").append(df.format(precision)).append("\t\t\t\n");
            }

            classes.forEach((recognizedClass, quantity) -> {
                result.append("\t" + recognizedClass + "  " + quantity + "\n");
            });
        });
        result.append("\n");

        result.append("accuracy = " + accuracy + "\n");
        result.append("Overall Time: " + overallTime + "s\n");
        result.append("\n-------------------------------------\n\n");

        StringBuilder finalString = new StringBuilder();
        finalString.append(result).append(resultFile);

        return finalString.toString();
    }

    private static void printStatistics(String[] args) {
        System.out.println(generateStatistics(args, false));
    }

    private static void writeToPlainFile(String filename, String value) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveGeneratedDataToFile(String[] args) {
        StringBuilder result = new StringBuilder(generateStatistics(args, true));

        StringBuilder filename = new StringBuilder();

        for (String it : args) {
            filename.append(it).append("_");
        }

        filename.append(LocalTime.now().getHour())
                .append("h_")
                .append(LocalTime.now().getMinute())
                .append("min_")
                .append(LocalTime.now().getSecond())
                .append("sek")
                .append(".txt");
        writeToPlainFile(filename.toString(), result.toString());
    }
}
