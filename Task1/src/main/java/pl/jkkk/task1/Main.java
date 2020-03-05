package pl.jkkk.task1;

import pl.jkkk.task1.reader.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;

import static pl.jkkk.task1.constant.Constants.FILENAME_LIST;

public class Main {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("abc");

        FileReader.readFromFiles(FILENAME_LIST);
    }
}
