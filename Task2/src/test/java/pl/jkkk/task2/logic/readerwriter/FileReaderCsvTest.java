package pl.jkkk.task2.logic.readerwriter;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static pl.jkkk.task2.logic.constant.LogicConstants.POLLUTION_DATA_FILENAME;

class FileReaderCsvTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private FileReaderCsv fileReaderCsv = new FileReaderCsv();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void readCsvTest() throws IOException, CsvException {
        fileReaderCsv.readCsv(POLLUTION_DATA_FILENAME);
    }
}
    