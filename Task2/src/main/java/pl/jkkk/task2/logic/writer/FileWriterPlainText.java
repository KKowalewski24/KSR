package pl.jkkk.task2.logic.writer;

import pl.jkkk.task2.logic.exception.FileOperationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class FileWriterPlainText<T extends Serializable> {

    /*------------------------ FIELDS REGION ------------------------*/
    private String filename;

    /*------------------------ METHODS REGION ------------------------*/
    public FileWriterPlainText(String filename) {
        this.filename = filename;
    }

    public void write(T object) throws FileOperationException {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(object.toString());
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }
}
    