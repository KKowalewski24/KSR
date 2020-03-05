package pl.jkkk.task1.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jkkk.task1.constant.Constants.SUBDIRECTORY;

class FileReaderTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Integer NUMBER_OF_DOCUMENTS_IN_FILE = 1000;

    private FileReader fileReader = new FileReader();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void numberOfObjectsTest() throws IOException, URISyntaxException {
        assertEquals(NUMBER_OF_DOCUMENTS_IN_FILE, fileReader.readFromFiles(
                Stream.of(SUBDIRECTORY + "reut2-000.sgm")
                        .collect(Collectors.toCollection(ArrayList::new))).size());
    }
}