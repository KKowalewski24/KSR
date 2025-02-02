package pl.jkkk.task1.reader;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderUtil {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/

    public static List<String> stringToListOfStringBySpace(String string) {
        return new ArrayList<>(Arrays.asList(string.split(" ")));
    }

    public static Node takeLastChildNode(Node node) {
        return node.childNode(node.childNodeSize() - 1);
    }
}
    
