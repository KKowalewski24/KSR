package pl.jkkk.task1;

import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.FileReader;
import pl.jkkk.task1.stemmer.DocumentStemmer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static FileReader fileReader = new FileReader();
    private static DocumentStemmer documentStemmer = new DocumentStemmer();
    private static List<Document> documents;

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) throws IOException, URISyntaxException {

        documents = fileReader.readFromFiles(FILENAME_LIST);
        documentStemmer.performStemmingProcessOnWordList(documents);

        /* Remove words from STOP-LIST */
        //TODO

    }
}
