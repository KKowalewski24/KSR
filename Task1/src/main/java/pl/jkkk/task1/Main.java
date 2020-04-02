package pl.jkkk.task1;

import pl.jkkk.task1.featureextraction.DocumentLengthFE;
import pl.jkkk.task1.featureextraction.FeatureExtractorDecorator;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.KeywordsExtractor;
import pl.jkkk.task1.featureextraction.Metric;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.RelativeNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.featureextraction.TermFrequencyMatrixFE;
import pl.jkkk.task1.featureextraction.TypeOfClassification;
import pl.jkkk.task1.featureextraction.UniqueNumberOfKeywordsInDocumentFragmentFE;
import pl.jkkk.task1.knn.KnnAlgorithm;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;

import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println(
                "Required parameters:  \nt" +
                        "\t<type of classification [features|tfm|ngram]>\n" +
                        "\t<percentage of training set (integer 1-99)>\n" +
                        "\t<k for kNN (integer >0)>\n" +
                        "\t(in case of features/tfm)\n" +
                        "\t\t<number of keywords (integer >0)>\n" +
                        "\t\t<metric (for features/tfm) [eucl|manh|cheb]>\n" +
                        "\t(in case of ngram)\n" +
                        "\t\t<number N (integer >0)"
        );
        System.exit(0);
    }

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) {
        /*----- RETRIEVE PROGRAM PARAMS -----*/
        TypeOfClassification typeOfClassification = null;
        int percentageOfTrainingSet = 0;
        int numberK = 0;
        int numberOfKeywords = 0;
        Metric metric = null;
        int numberN = 0;
        try {
            typeOfClassification = TypeOfClassification.fromString(args[0]);
            percentageOfTrainingSet = Integer.valueOf(args[1]);
            numberK = Integer.valueOf(args[2]);
            if (typeOfClassification == TypeOfClassification.NGRAM) {
                numberN = Integer.valueOf(args[3]);
            } else {
                numberOfKeywords = Integer.valueOf(args[3]);
                metric = Metric.convertAbbreviationToMetric(args[4]);
            }
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
        extractFeatures(typeOfClassification);
        knnClassification(numberK, metric);

        /*----- SUMMARY -----*/
        printStatistics();
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
        Random random = new Random();
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

    private static void extractFeatures(TypeOfClassification typeOfClassification) {
        trainingFeatureVectors = new ArrayList<>();
        testFeatureVectors = new ArrayList<>();

        extractorDecorator = new FeatureExtractorDecorator();
        if (typeOfClassification == TypeOfClassification.FEATURES) {
            extractorDecorator.addExtractor(new DocumentLengthFE());
            keywordsSets.forEach(keywords -> {
                extractorDecorator.addExtractor(
                        new UniqueNumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
                extractorDecorator.addExtractor(
                        new UniqueNumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
                extractorDecorator.addExtractor(
                        new NumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
                extractorDecorator.addExtractor(
                        new NumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
                extractorDecorator.addExtractor(
                        new RelativeNumberOfKeywordsInDocumentFragmentFE(keywords, 0, 50));
                extractorDecorator.addExtractor(
                        new RelativeNumberOfKeywordsInDocumentFragmentFE(keywords, 50, 100));
            });
        } else if (typeOfClassification == TypeOfClassification.TFM) {
            extractorDecorator.addExtractor(new TermFrequencyMatrixFE(keywordsSets.get(0)));
        }

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

    private static void knnClassification(int numberK, Metric metric) {
        action(() -> {
            classification = new HashMap<>();
            for (FeatureVector it : testFeatureVectors) {
                final String properPlace = it.getDocument().getPlaceList().get(0);
                final String recognizedPlace = knnAlgorithm
                        .calculateAndClassify(it, trainingFeatureVectors, numberK, metric);
                if (!classification.containsKey(properPlace)) {
                    classification.put(properPlace, new HashMap<>());
                }
                classification.get(properPlace)
                        .put(recognizedPlace, classification.get(properPlace)
                                .getOrDefault(recognizedPlace, 0) + 1);
            }

        }, "kNN");
    }

    private static String generateStatistics() {
        StringBuilder result = new StringBuilder();

        result.append("\n-------------------------------------\n");
        classification.forEach((clazz, classes) -> {
            double percent = classes.getOrDefault(clazz, 0) * 100.0 / classes.values()
                    .stream()
                    .mapToInt(x -> x)
                    .sum();
            result.append(clazz + "   " + percent + " %\n");
            classes.forEach((recognizedClass, quantity) -> {
                result.append("\t" + recognizedClass + "  " + quantity + "\n");
            });
        });
        result.append("\n");

        final int allSum =
                classification.values()
                        .stream()
                        .mapToInt(classes -> classes.values().stream().mapToInt(x -> x).sum())
                        .sum();
        final int properlyClassifiedSum = classification.keySet().stream().mapToInt(
                clazz -> classification.get(clazz)
                        .keySet()
                        .stream()
                        .filter(recognizedClass -> recognizedClass.equals(clazz))
                        .mapToInt(recognizedClass -> classification.get(clazz).get(recognizedClass))
                        .sum()).sum();
        result.append("All: " + (properlyClassifiedSum * 100.0 / allSum) + " %\n");
        result.append("Overall Time: " + overallTime + "s\n");
        result.append("\n-------------------------------------\n\n");

        return result.toString();
    }

    private static void printStatistics() {
        System.out.println(generateStatistics());
    }

    private static void writeToPlainFile(String filename, String value) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveGeneratedDataToFile(String[] args) {
        /*TODO FILL WITH REAL DATA*/
        StringBuilder result = new StringBuilder("\\begin{table}[H]\n"
                + "\t\\centering\n"
                + "\t\\begin{tabular}{c c c c} \n"
                + "\t\t\\hline\n"
                + "\t\t\\textbf{k} & \\textbf{places [\\%]} & \\textbf{topics [\\%]} &  "
                + "\\textbf{authors "
                + "[\\%]} \\\\ [0.5ex] \n"
                + "\t\t\\hline\n"
                + "\t\t\\hline \n"
                + "\t\t2 & 74.4 & 53.7 & 43.9 \\\\ \n"
                + "\t\t3 & 78.5 & 52.2 & 43.9 \\\\\n"
                + "\t\t5 & 80.2 & 52.2 & 36.6 \\\\\n"
                + "\t\t7 & 81.0 & 53.7 & 26.8 \\\\\n"
                + "\t\t10 & 81.5 & 60.4 & 24.4 \\\\\n"
                + "\t\t15 & 81.6 & 62.7 & 29.3 \\\\\n"
                + "\t\t20 & 81.4 & 61.2 & 31.7 \\\\ \n"
                + "\t\t\\hline\n"
                + "\t\\end{tabular}\n"
                + "\t\\caption{Skuteczność klasyfikacji dla metryki Euklidesowej dla pierwszego "
                + "sposobu "
                + "ekstrakcji}\n"
                + "\\end{table}");

        result.append("\n\n-------------------------------\n\n");

        result.append(generateStatistics());

        StringBuilder filename = new StringBuilder();

        for (String it : args) {
            filename.append(it).append("_");
        }

        filename.append(LocalTime.now().getHour() + "h_" + LocalTime.now().getMinute()
                + "min_" + LocalTime.now().getSecond() + "sek");
        filename.append(".txt");
        writeToPlainFile(filename.toString(), result.toString());
    }
}
