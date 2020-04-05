package pl.jkkk.task2.logic.writer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileWriterPlainTextTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String testFilename = "xxx.txt";

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
        FileWriterPlainText<Sample> fileWriterPlainText = new FileWriterPlainText<>(testFilename);

        fileWriterPlainText.write(new Sample());
    }
}