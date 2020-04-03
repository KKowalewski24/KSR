package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.exception.NoDocumentsException;
import pl.jkkk.task1.exception.NoOccurrencesException;
import pl.jkkk.task1.model.Document;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KeywordsExtractor {

    /*------------------------ FIELDS REGION ------------------------*/
    private Map<String, Map<Document, Integer>> termFrequencyPerDocument;
    private Map<String, Map<String, Integer>> termFrequencyPerClass;
    private Map<String, Integer> classesQuantity;

    /* Score (from some algorithm) for each word */
    private Map<String, Double> scores;

    /* All processed documents */
    private List<Document> documents;

    /*------------------------ METHODS REGION ------------------------*/
    public KeywordsExtractor(final List<Document> documents) {
        this.termFrequencyPerDocument = new HashMap<>();
        this.termFrequencyPerClass = new HashMap<>();
        this.classesQuantity = new HashMap<>();
        this.scores = new HashMap<>();
        this.documents = documents;
    }

    /**
     * Calculate term frequency per each document,
     * must be called before any keywords retrieving method.
     * param documents list of documents
     */
    public void calculateOccurrences() {
        if (documents.isEmpty()) {
            throw new NoDocumentsException();
        }

        termFrequencyPerDocument.clear();
        termFrequencyPerClass.clear();
        classesQuantity.clear();

        documents.forEach((document) -> {
            document.getWordList().forEach((word) -> {
                if (!termFrequencyPerDocument.containsKey(word)) {
                    termFrequencyPerDocument.put(word, new HashMap<>());
                }

                termFrequencyPerDocument.get(word)
                        .put(document, termFrequencyPerDocument.get(word)
                                .getOrDefault(document, 0) + 1);
            });
        });

        termFrequencyPerDocument.forEach((word, documents) -> {
            termFrequencyPerClass.put(word, new HashMap<>());
            documents.forEach((document, quantity) -> {
                final String clazz = document.getPlaceList().get(0);
                classesQuantity.put(clazz, classesQuantity.getOrDefault(clazz, 0) + quantity);
                termFrequencyPerClass.get(word)
                        .put(clazz, termFrequencyPerClass.get(word)
                                .getOrDefault(clazz, 0) + quantity);
            });
        });
    }

    private void calculateScoresUsingClassTFIOCTF() {
        if (termFrequencyPerClass.isEmpty()) {
            throw new NoOccurrencesException();
        }
        termFrequencyPerClass.keySet().forEach(word -> scores.put(word, Double.MIN_VALUE));

        termFrequencyPerClass.forEach((word, classes) -> {
            classes.forEach((clazz, quantity) -> {
                /* calculate term frequency in this class */
                final double TF = quantity / (double) classesQuantity.get(clazz);
                /* calculate term frequency in the rest of classes */
                final double otherClassTF = classes.keySet()
                        .stream()
                        .filter(key -> !key.equals(clazz))
                        .mapToDouble(key -> classes.get(key) / (double) classesQuantity.get(key))
                        .sum();
                final double TFIOCTF = TF * Math.log(1.0 / otherClassTF);
                if (TFIOCTF > scores.get(word)) {
                    scores.put(word, TFIOCTF);
                }
            });
        });
    }

    private void calculateScoresUsingTFIDF() {
        if (termFrequencyPerDocument.isEmpty()) {
            throw new NoOccurrencesException();
        }
        termFrequencyPerDocument.keySet().forEach(word -> scores.put(word, Double.MIN_VALUE));

        termFrequencyPerDocument.forEach((word, wordDocuments) -> {
            wordDocuments.forEach((document, quantity) -> {
                final double tf = quantity / (double) document.getWordList().size();
                final double df = wordDocuments.size() / (double) documents.size();
                final double tfidf = tf * Math.log(1.0 / df);
                if (tfidf > scores.get(word)) {
                    scores.put(word, tfidf);
                }
            });
        });
    }

    private Set<String> retrieveKeywords(final int numberOfKeywords) {
        return scores.keySet()
                .stream()
                .sorted(Comparator.comparing((String word) -> scores.get(word)))
                .limit(numberOfKeywords)
                .collect(Collectors.toSet());
    }

    public Set<String> getKeywordsByTFIDF(final int numberOfKeywords) {
        calculateScoresUsingTFIDF();
        return retrieveKeywords(numberOfKeywords);
    }

    public Set<String> getKeywordsByClassTFIOCTF(final int numberOfKeywords) {
        calculateScoresUsingClassTFIOCTF();
        return retrieveKeywords(numberOfKeywords);
    }
}
