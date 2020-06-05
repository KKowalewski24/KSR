package pl.jkkk.task2.logic.readerwriter;

import pl.jkkk.task2.logic.exception.FileOperationException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class FileWriterPlainText {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String TXT = ".txt";

    /*------------------------ METHODS REGION ------------------------*/
    public void writePlainText(String filename, String value) throws FileOperationException {
        write(filename, value);
    }

    public void writePlainText(String filename, List<String> values) throws FileOperationException {
        StringBuilder stringBuilderValues = new StringBuilder();
        values.forEach((it) -> stringBuilderValues.append(it).append("\n"));

        write(filename.replaceAll(" ", "_"), stringBuilderValues.toString());
    }

    public void writePlainText(List<String> filenames, String value) throws FileOperationException {
        StringBuilder stringBuilder = new StringBuilder();
        filenames.forEach((it) -> {
            stringBuilder.append(it);
        });

        write(stringBuilder.toString().replaceAll(" ", "_"), value);
    }

    public void writePlainText(List<String> filenames, List<String> values)
            throws FileOperationException {
        StringBuilder stringBuilderFilenames = new StringBuilder();
        filenames.forEach((it) -> stringBuilderFilenames.append(it));

        StringBuilder stringBuilderValues = new StringBuilder();
        values.forEach((it) -> stringBuilderValues.append(it).append("\n"));

        write(stringBuilderFilenames.toString().replaceAll(" ", "_"),
                stringBuilderValues.toString());
    }

    private void write(String filename, String value) throws FileOperationException {
        try (FileWriter fileWriter = new FileWriter(generateFilename(filename, TXT))) {
            fileWriter.write(value);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }

    private String generateFilename(String type, String fileExtension) {
        return new StringBuilder()
                .append(type)
                .append("_")
                .append(LocalTime.now().getHour())
                .append(LocalTime.now().getMinute())
                .append(LocalTime.now().getSecond())
                .append(fileExtension)
                .toString();
    }
}
