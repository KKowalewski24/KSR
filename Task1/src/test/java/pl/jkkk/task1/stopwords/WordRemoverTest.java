//package pl.jkkk.task1.stopwords;
//
//import org.junit.jupiter.api.Test;
//import pl.jkkk.task1.exception.ReaderException;
//import pl.jkkk.task1.model.Document;
//
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class WordRemoverTest {
//
//    /*------------------------ FIELDS REGION ------------------------*/
//    public static final String REMOVE = "remove";
//    public static final String ABLE = "able";
//    public static final String ABROAD = "abroad";
//
//    private WordRemover wordRemover = new WordRemover();
//
//    private List<Document> documentsInput = Stream.of(
//            new Document(null, null, null, Stream.of(REMOVE, ABLE, ABROAD)
//                    .collect(Collectors.toCollection(ArrayList::new))),
//            new Document(null, null, null, Stream.of(REMOVE, ABLE, ABROAD)
//                    .collect(Collectors.toCollection(ArrayList::new))),
//            new Document(null, null, null, Stream.of(REMOVE, ABLE, ABROAD)
//                    .collect(Collectors.toCollection(ArrayList::new))),
//            new Document(null, null, null, Stream.of(REMOVE, ABLE, ABROAD)
//                    .collect(Collectors.toCollection(ArrayList::new)))
//    ).collect(Collectors.toCollection(ArrayList::new));
//
//    private List<Document> documentsOutput = Stream.of(
//            new Document(null, null, null, Stream.of(REMOVE)
//                    .collect(Collectors.toCollection(ArrayList::new))),
//            new Document(null, null, null, Stream.of(REMOVE)
//                    .collect(Collectors.toCollection(ArrayList::new))),
//            new Document(null, null, null, Stream.of(REMOVE)
//                    .collect(Collectors.toCollection(ArrayList::new))),
//            new Document(null, null, null, Stream.of(REMOVE)
//                    .collect(Collectors.toCollection(ArrayList::new)))
//    ).collect(Collectors.toCollection(ArrayList::new));
//
//    /*------------------------ METHODS REGION ------------------------*/
//    @Test
//    void removeStopWordsTest() throws URISyntaxException, ReaderException {
//        wordRemover.removeStopWords(documentsInput);
//        assertEquals(documentsOutput, documentsInput);
//    }
//}
