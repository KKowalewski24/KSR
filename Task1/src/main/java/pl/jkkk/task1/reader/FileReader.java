package pl.jkkk.task1.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import pl.jkkk.task1.model.Document;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String MAIN_NODE = "REUTERS";

    public static final String TOPICS_NODE = "TOPICS";
    public static final String PLACES_NODE = "PLACES";
    public static final String D_NODE = "D";
    public static final String TEXT_NODE = "TEXT";
    public static final String TITLE_NODE = "TITLE";

    /*------------------------ METHODS REGION ------------------------*/
    private static Path preparePath(String filename) throws URISyntaxException {
        return Paths.get(Thread
                .currentThread()
                .getContextClassLoader()
                .getResource(filename)
                .toURI());
    }

    private static List<String> stringToListOfStringBySpace(String string) {
        return new ArrayList<>(Arrays.asList(string.split(" ")));
    }

    private static Node takeLastChildNode(Node node) {
        return node.childNode(node.childNodeSize() - 1);
    }

    public static List<Document> readFromFiles(List<String> filenameList)
            throws IOException, URISyntaxException {
        List<Document> documentList = new ArrayList();

        for (String it : filenameList) {
            Jsoup.parse(new File(String.valueOf(preparePath(it))), CHARSET_UTF_8)
                    .select(MAIN_NODE)
                    .forEach((jt) -> {
                        Element textNodeElement = jt.getElementsByTag(TEXT_NODE).first();

                        documentList.add(new Document(
                                jt.getElementsByTag(TOPICS_NODE).first()
                                        .getElementsByTag(D_NODE).eachText(),
                                jt.getElementsByTag(PLACES_NODE).first()
                                        .getElementsByTag(D_NODE).eachText(),
                                textNodeElement.getElementsByTag(TITLE_NODE).text(),
                                stringToListOfStringBySpace(
                                        takeLastChildNode(textNodeElement).toString())
                        ));
                    });
        }

        return documentList;
    }
}
    