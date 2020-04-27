package pl.jkkk.task2.logic.readerwriter;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import pl.jkkk.task2.logic.model.Pollution;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderCsv {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public List<Pollution> readCsv(String filename) throws IOException, CsvException {
        Reader reader = new InputStreamReader(getClass().getResourceAsStream(filename));

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .build();

        List<String[]> fileRows = csvReader.readAll();

        List<Pollution> pollutions = new ArrayList<>();

        System.out.println(fileRows.size());

        return pollutions;
    }
}
    