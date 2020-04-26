package pl.jkkk.task2.logic.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileWriterPlainTextTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String testFilename = "xxx";

    private static class Sample implements Serializable {
        int number = 5;
        String string = "bgbfgbbbfbfgffdfsdfs";

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("number: ")
                    .append(number)
                    .append(", string: ")
                    .append(string)
                    .toString();
        }
    }

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void writeTest() throws IOException {
        Files.deleteIfExists(Paths.get(testFilename));
        FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

        fileWriterPlainText.writePlainText(testFilename, new Sample().toString());
    }
}