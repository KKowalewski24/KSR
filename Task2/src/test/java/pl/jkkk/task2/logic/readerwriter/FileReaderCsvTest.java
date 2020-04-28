package pl.jkkk.task2.logic.readerwriter;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import pl.jkkk.task2.logic.model.Pollution;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pl.jkkk.task2.logic.constant.LogicConstants.POLLUTION_DATA_FILENAME;

class FileReaderCsvTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private FileReaderCsv fileReaderCsv = new FileReaderCsv();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void readCsvTest() throws IOException, CsvException {
        List<Pollution> pollutions = fileReaderCsv.readCsv(POLLUTION_DATA_FILENAME, false);
        assertNotNull(pollutions);
    }

    @Test
    void readCsvSkipFirstLineTest() throws IOException, CsvException {
        List<Pollution> pollutions = fileReaderCsv.readCsv(POLLUTION_DATA_FILENAME, true);
        assertNotNull(pollutions);
    }
}
    