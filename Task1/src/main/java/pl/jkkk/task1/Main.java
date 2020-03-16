package pl.jkkk.task1;

import java.util.ArrayList;
import java.util.List;

import pl.jkkk.task1.featureextraction.KeywordsExtractor;
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

        final List<String> keywords = new ArrayList<>();
        action(() -> keywords.addAll(keywordsExtractor.getKeywordsByTPSD(100)),
                "Retrieving keywords");
    }

    private static void action(Runnable runnable, String description){
        long startTime = System.currentTimeMillis();
        System.out.print(description + "... ");
        runnable.run();
        System.out.println(((System.currentTimeMillis() - startTime) / 1000.0) + "s");
    }
}
