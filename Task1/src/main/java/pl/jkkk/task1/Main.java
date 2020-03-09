package pl.jkkk.task1;

import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.JsonFileReader;
import pl.jkkk.task1.reader.SgmlFileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;
import static pl.jkkk.task1.constant.Constants.STOP_WORDS;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static SgmlFileReader sgmlFileReader = new SgmlFileReader();
    private static DocumentStemmer documentStemmer = new DocumentStemmer();
    private static List<Document> documents;

    private static JsonFileReader jsonFileReader = new JsonFileReader();

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) throws IOException, URISyntaxException {

        documents = sgmlFileReader.readFromFiles(FILENAME_LIST);
        documentStemmer.performStemmingProcessOnWordList(documents);

        /* Remove words from STOP-LIST */

        jsonFileReader.readFromFile(STOP_WORDS).forEach(it -> System.out.println(it));
    }
}
