package pl.jkkk.task1.stopwords;

import pl.jkkk.task1.exception.ReaderException;
import pl.jkkk.task1.model.Document;
import pl.jkkk.task1.reader.JsonFileReader;

import java.net.URISyntaxException;
import java.util.List;

import static pl.jkkk.task1.constant.Constants.STOP_WORDS;

public class WordRemover {

    /*------------------------ FIELDS REGION ------------------------*/
    private JsonFileReader jsonFileReader = new JsonFileReader();
    private List<String> stopWords;

    /*------------------------ METHODS REGION ------------------------*/
    public void removeStopWords(List<Document> documents)
            throws URISyntaxException, ReaderException {
        stopWords = jsonFileReader.readFromFile(STOP_WORDS);

        for (Document it : documents) {
            it.getWordList().removeIf((jt) -> stopWords.contains(jt));
        }
    }
}
    