package pl.jkkk.task1.stemmer;

import opennlp.tools.stemmer.PorterStemmer;
import pl.jkkk.task1.model.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentStemmer {

    /*------------------------ FIELDS REGION ------------------------*/
    private PorterStemmer porterStemmer = new PorterStemmer();

    /*------------------------ METHODS REGION ------------------------*/
    public void performStemmingProcessOnWordList(List<Document> documents) {
        for (Document it : documents) {
            List<String> stemmedWords = new ArrayList<>();

            it.getWordList().forEach((jt) -> stemmedWords.add(porterStemmer.stem(jt)));
            it.setWordList(stemmedWords);
        }
    }
}
    