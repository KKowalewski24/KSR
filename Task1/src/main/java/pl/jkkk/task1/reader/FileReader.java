package pl.jkkk.task1.reader;

import pl.jkkk.task1.model.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public static List<Document> readFromFiles(List<String> filenameList)
            throws URISyntaxException, IOException {
        List<Document> documentList = new ArrayList();

//        TODO REMOVE STRINGBUILDER + ADD PARSING TO OBJECT
        StringBuilder stringBuilder = new StringBuilder();

        for (String it : filenameList) {
            Path path = Paths.get(Thread
                    .currentThread()
                    .getContextClassLoader()
                    .getResource(it)
                    .toURI());

            try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())) {
                lines.forEach((jt) -> stringBuilder.append(jt));
            }
        }

        return documentList;
    }
}
    