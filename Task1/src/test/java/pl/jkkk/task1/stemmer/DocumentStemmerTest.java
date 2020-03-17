package pl.jkkk.task1.stemmer;

import org.junit.jupiter.api.Test;
import pl.jkkk.task1.model.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentStemmerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String DOING = "doing";
    public static final String RUNNING = "running";
    public static final String DO = "do";
    public static final String RUN = "run";

    private DocumentStemmer documentStemmer = new DocumentStemmer();

    private List<Document> documentsInput = Stream.of(
            new Document(null, null, null, Stream.of(DOING, RUNNING)
                    .collect(Collectors.toCollection(ArrayList::new))),
            new Document(null, null, null, Stream.of(DOING, RUNNING)
                    .collect(Collectors.toCollection(ArrayList::new))),
            new Document(null, null, null, Stream.of(DOING, RUNNING)
                    .collect(Collectors.toCollection(ArrayList::new))),
            new Document(null, null, null, Stream.of(DOING, RUNNING)
                    .collect(Collectors.toCollection(ArrayList::new)))
    ).collect(Collectors.toCollection(ArrayList::new));

    private List<Document> documentsOutput = Stream.of(
            new Document(null, null, null, Stream.of(DO, RUN)
                    .collect(Collectors.toCollection(ArrayList::new))),
            new Document(null, null, null, Stream.of(DO, RUN)
                    .collect(Collectors.toCollection(ArrayList::new))),
            new Document(null, null, null, Stream.of(DO, RUN)
                    .collect(Collectors.toCollection(ArrayList::new))),
            new Document(null, null, null, Stream.of(DO, RUN)
                    .collect(Collectors.toCollection(ArrayList::new)))
    ).collect(Collectors.toCollection(ArrayList::new));

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void performStemmingProcessOnWordListTest() {
        documentStemmer.performStemmingProcessOnWordList(documentsInput);
        assertEquals(documentsOutput, documentsInput);
    }
}