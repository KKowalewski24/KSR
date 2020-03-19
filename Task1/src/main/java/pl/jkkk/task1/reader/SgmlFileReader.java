package pl.jkkk.task1.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import pl.jkkk.task1.model.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static pl.jkkk.task1.reader.ReaderUtil.stringToListOfStringBySpace;
import static pl.jkkk.task1.reader.ReaderUtil.takeLastChildNode;

public class SgmlFileReader {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String MAIN_NODE = "REUTERS";

    public static final String TOPICS_NODE = "TOPICS";
    public static final String PLACES_NODE = "PLACES";
    public static final String D_NODE = "D";
    public static final String TEXT_NODE = "TEXT";
    public static final String TITLE_NODE = "TITLE";

    /*------------------------ METHODS REGION ------------------------*/
    public List<Document> readFromFiles(List<String> filenameList) throws IOException {
        List<Document> documentList = new ArrayList();

        for (String it : filenameList) {
            Jsoup.parse(getClass().getResourceAsStream(it), CHARSET_UTF_8, "")
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
    
