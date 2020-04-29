package pl.jkkk.task2.logic.readerwriter;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.model.Pollution;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileReaderCsv {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public List<Pollution> readCsv(String filename,
                                   boolean isSkippingFirstRow) throws FileOperationException {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream(filename));
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

            if (isSkippingFirstRow) {
                csvReader.skip(1);
            }

            List<String[]> fileRows = csvReader.readAll();
            List<Pollution> pollutions = new ArrayList<>();

            fileRows.forEach((it) -> {
                int argCounter = 0;
                pollutions.add(new Pollution(
                        Integer.valueOf(it[argCounter++]),
                        Integer.valueOf(it[argCounter++]),
                        Integer.valueOf(it[argCounter++]),
                        it[argCounter++],
                        it[argCounter++],
                        it[argCounter++],
                        it[argCounter++],
                        LocalDate.parse(it[argCounter++]),

                        it[argCounter++],
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),

                        it[argCounter++],
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),

                        it[argCounter++],
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),

                        it[argCounter++],
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++]),
                        Double.valueOf(it[argCounter++])
                ));
            });

            return pollutions;
        } catch (IOException | CsvException e) {
            throw new FileOperationException(e);
        }
    }
}
    