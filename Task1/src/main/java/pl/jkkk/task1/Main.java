package pl.jkkk.task1;

import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;
import pl.jkkk.task1.stopwords.WordRemover;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static SgmlFileReader sgmlFileReader = new SgmlFileReader();
    private static DocumentStemmer documentStemmer = new DocumentStemmer();
    private static WordRemover wordRemover = new WordRemover();
    private static List<Document> documents;

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) throws IOException, URISyntaxException {

        documents = sgmlFileReader.readFromFiles(FILENAME_LIST);
        documentStemmer.performStemmingProcessOnWordList(documents);
        wordRemover.removeStopWords(documents);
    }
}
