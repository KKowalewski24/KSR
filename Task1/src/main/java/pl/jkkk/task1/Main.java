package pl.jkkk.task1;

import pl.jkkk.task1.featureextraction.FeatureExtractorDecorator;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.KeywordsExtractor;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsFE;
import pl.jkkk.task1.featureextraction.RelativeNumberOfKeywordsFE;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static SgmlFileReader sgmlFileReader = new SgmlFileReader();
    private static DocumentStemmer documentStemmer = new DocumentStemmer();
    private static WordRemover wordRemover = new WordRemover();
    private static KeywordsExtractor keywordsExtractor;
    private static FeatureExtractorDecorator extractorDecorator;
    private static List<Document> documents;
    private static Set<String> keywords;
    private static List<FeatureVector> featureVectors;
    private static double overallTime = 0;

    //    podział na podzbiory uczący i testowy 60/40 z 1 args i wylosowac liczby - podział na
    //    zbiory
    //    propocje dzielenia dokumentu
    //    dla kazdego zbioru elelmentu testowego robie klasuyfikacje w knn
    //    spradzic czy dobrze zaklasyfikowane są i wyliczne
    /*------------------------ METHODS REGION ------------------------*/
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

    private static void extractFeatures() {
        featureVectors = new ArrayList<>();
        extractorDecorator = new FeatureExtractorDecorator();

        extractorDecorator.addExtractor(new NumberOfKeywordsFE(keywords));
        extractorDecorator.addExtractor(new RelativeNumberOfKeywordsFE(keywords));

        action(() -> featureVectors.addAll(documents.stream()
                        .map(extractorDecorator::extract)
                        .collect(Collectors.toList())),
                "Features extracting");
    }

    public static void main(String[] args) {

        readDocuments();

        filterDocuments();

        stemDocuments();

        removeStopWords();

        calculateWordOccurrences();

        retrieveKeywords();

        //        podział i feature exctracting dla dwóch zbiorów

        extractFeatures();

        //robie for na zbiorze testowym i wybrór  metryki w argumentach

        action(() -> {
            ;
        }, "KNN");

        //        wypisac statystyki

        System.out.println("\nOverall Time: " + overallTime + "s");
    }
}
