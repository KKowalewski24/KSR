package pl.jkkk.task2.logic.readerwriter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.jkkk.task2.logic.exception.FileOperationException;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Disabled
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

    @Test
    void writeListTest() throws IOException {
        Files.deleteIfExists(Paths.get(testFilename));
        FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

        List listTrimmed = new ArrayList<>(Stream.of("abc", "cde")
                .collect(Collectors.toList())).subList(0, 2);
        fileWriterPlainText.writePlainText(testFilename, listTrimmed);
    }

    @Test
    void writePlainTextTest() throws FileOperationException {
        FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();
        fileWriterPlainText.writePlainText(
                Stream.of(
                        "napis spracja",
                        "aaa ccc"
                ).collect(Collectors.toList()),
                "abcde");
    }
}
