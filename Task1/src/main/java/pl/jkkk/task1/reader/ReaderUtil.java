package pl.jkkk.task1.reader;

import org.jsoup.nodes.Node;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderUtil {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public static Path preparePath(String filename) throws URISyntaxException {
        return Paths.get(Thread
                .currentThread()
                .getContextClassLoader()
                .getResource(filename)
                .toURI());
    }

    public static List<String> stringToListOfStringBySpace(String string) {
        return new ArrayList<>(Arrays.asList(string.split(" ")));
    }

    public static Node takeLastChildNode(Node node) {
        return node.childNode(node.childNodeSize() - 1);
    }
}
    