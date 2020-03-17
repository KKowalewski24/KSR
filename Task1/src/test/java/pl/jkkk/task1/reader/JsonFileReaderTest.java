package pl.jkkk.task1.reader;

import org.junit.jupiter.api.Test;
import pl.jkkk.task1.exception.ReaderException;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jkkk.task1.constant.Constants.STOP_WORDS;

class JsonFileReaderTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private JsonFileReader jsonFileReader = new JsonFileReader();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void readFromFileTest() throws URISyntaxException, ReaderException {
        assertEquals(853, jsonFileReader.readFromFile(STOP_WORDS).size());
    }
}