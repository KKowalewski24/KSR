package pl.jkkk.task2.logic.writer;

import pl.jkkk.task2.logic.exception.FileOperationException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class FileWriterPlainText {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String TXT = ".txt";

    /*------------------------ METHODS REGION ------------------------*/
    public String generateFilename(String type, String fileExtension) {
        StringBuilder result = new StringBuilder();
        result.append(type)
                .append("_")
                .append(LocalTime.now().getHour())
                .append("h_")
                .append(LocalTime.now().getMinute())
                .append("min_")
                .append(LocalTime.now().getSecond())
                .append("sek")
                .append(fileExtension);

        return result.toString();
    }

    private void write(String filename, String value) throws FileOperationException {
        try (FileWriter fileWriter = new FileWriter(generateFilename(filename, TXT))) {
            fileWriter.write(value);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }

    public void writePlainText(String filename, String value) throws FileOperationException {
        write(filename, value);
    }

    public void writePlainText(List<String> filenames, String value) throws FileOperationException {
        StringBuilder stringBuilder = new StringBuilder();
        filenames.forEach((it) -> stringBuilder.append(it));

        write(stringBuilder.toString(), value);
    }
}
    