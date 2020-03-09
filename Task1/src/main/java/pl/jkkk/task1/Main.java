package pl.jkkk.task1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import pl.jkkk.task1.reader.FileReader;
import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    private static FileReader fileReader = new FileReader();

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) throws IOException, URISyntaxException {

        /* Read documents */
        List<Document> documents = fileReader.readFromFiles(FILENAME_LIST);
        
        /* Stemme documents */
        //TODO

        /* Remove words from STOP-LIST */
        //TODO
        
    }
}
