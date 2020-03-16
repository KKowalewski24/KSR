package pl.jkkk.task1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import pl.jkkk.task1.featureextraction.FeatureExtractorDecorator;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.KeywordsExtractor;
import pl.jkkk.task1.featureextraction.NumberOfKeywordsFE;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;
import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static SgmlFileReader sgmlFileReader = new SgmlFileReader();
    private static DocumentStemmer documentStemmer = new DocumentStemmer();
    private static WordRemover wordRemover = new WordRemover();
    private static KeywordsExtractor keywordsExtractor;
    private static List<Document> documents;

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args){

        action(() -> {
            try{
                documents = sgmlFileReader.readFromFiles(FILENAME_LIST);
            }catch(Exception e){
                throw new RuntimeException(e);
            }}, "Reading documents");

        action(() -> documentStemmer.performStemmingProcessOnWordList(documents),
                "Stemming documents");

        action(() -> {
            try{
                wordRemover.removeStopWords(documents);
            }catch(Exception e){
                throw new RuntimeException(e);
            }}, "Removing stop words");

        keywordsExtractor = new KeywordsExtractor(documents);
        action(() -> keywordsExtractor.calculateOccurrences(),
                "Calculating word occurrences");

        final Set<String> keywords = new HashSet<>();
        action(() -> keywords.addAll(keywordsExtractor.getKeywordsByTPSD(100)),
                "Retrieving keywords");

        final Set<FeatureVector> featureVectors = new HashSet<>();
        action(() -> {
                FeatureExtractorDecorator extractorDecorator = 
                    new FeatureExtractorDecorator();
                extractorDecorator.addExtractor(new NumberOfKeywordsFE(keywords));
                featureVectors.addAll(documents.stream()
                    .map(document -> extractorDecorator.extract(document))
                    .collect(Collectors.toSet()));
            }, "Features extracting");
    }

    private static void action(Runnable runnable, String description){
        long startTime = System.currentTimeMillis();
        System.out.print(description + "... ");
        runnable.run();
        System.out.println(((System.currentTimeMillis() - startTime) / 1000.0) + "s");
    }
}
