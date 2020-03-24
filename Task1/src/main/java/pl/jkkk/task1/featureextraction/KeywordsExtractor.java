package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.exception.NoDocumentsException;
import pl.jkkk.task1.exception.NoOccurrencesException;
import pl.jkkk.task1.model.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class KeywordsExtractor {

    /*------------------------ FIELDS REGION ------------------------*/
    private Map<String, Map<Document, Integer>> termFrequencyPerDocument;

    /* Score (from some algorithm) for each word */
    private Map<String, Double> scores;

    /* All processed documents */
    private List<Document> documents;

    /*------------------------ METHODS REGION ------------------------*/
    public KeywordsExtractor(final List<Document> documents) {
        this.termFrequencyPerDocument = new HashMap<>();
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

        documents.forEach((document) -> {
            document.getWordList().forEach((word) -> {
                if (!termFrequencyPerDocument.containsKey(word)) {
                    termFrequencyPerDocument.put(word, new HashMap<>());
                }

                termFrequencyPerDocument.get(word).put(document,
                        Optional.ofNullable(termFrequencyPerDocument.get(word).get(document))
                                .orElse(0) + 1);
            });
        });
    }

    private void calculateScoresUsingClassTF() {
        if (termFrequencyPerDocument.isEmpty()) {
            throw new NoOccurrencesException();
        }
        termFrequencyPerDocument.keySet().forEach(word -> scores.put(word, Double.MIN_VALUE));

        Set<String> classes = new HashSet<>();
        final Map<String, Map<String, Integer>> termFrequencyPerClass = new HashMap<>();
        termFrequencyPerDocument.keySet().forEach(word -> {
            termFrequencyPerClass.put(word, new HashMap<>());
            termFrequencyPerDocument.get(word).keySet().forEach(document -> {
                classes.add(document.getPlaceList().get(0));
                termFrequencyPerClass.get(word).put(document.getPlaceList().get(0),
                        Optional.ofNullable(termFrequencyPerClass.get(word)
                                .get(document.getPlaceList().get(0)))
                                .orElse(0) + termFrequencyPerDocument.get(word).get(document));
            });
        });
        classes.forEach(clazz -> {
            double numberOfWordsInClass = termFrequencyPerClass.values()
                    .stream()
                    .mapToInt(map -> Optional.ofNullable(map.get(clazz)).orElse(0))
                    .sum();
            termFrequencyPerDocument.keySet().forEach(word -> {
                if (termFrequencyPerClass.get(word).get(clazz) != null) {
                    double tf = termFrequencyPerClass.get(word).get(clazz) / numberOfWordsInClass;
                    if (tf > scores.get(word)) {
                        scores.put(word, tf);
                    }
                }
            });
        });
    }

    private void calculateScoresUsingClassTFIDF() {
        if (termFrequencyPerDocument.isEmpty()) {
            throw new NoOccurrencesException();
        }
        termFrequencyPerDocument.keySet().forEach(word -> scores.put(word, Double.MIN_VALUE));

        Set<String> classes = new HashSet<>();
        final Map<String, Map<String, Integer>> termFrequencyPerClass = new HashMap<>();
        final Map<String, Map<String, Integer>> documentFrequencyPerClass = new HashMap<>();
        termFrequencyPerDocument.keySet().forEach(word -> {
            termFrequencyPerClass.put(word, new HashMap<>());
            documentFrequencyPerClass.put(word, new HashMap<>());
            termFrequencyPerDocument.get(word).keySet().forEach(document -> {
                classes.add(document.getPlaceList().get(0));
                termFrequencyPerClass.get(word).put(document.getPlaceList().get(0),
                        Optional.ofNullable(termFrequencyPerClass.get(word)
                                .get(document.getPlaceList().get(0)))
                                .orElse(0) + termFrequencyPerDocument.get(word).get(document));
                documentFrequencyPerClass.get(word).put(document.getPlaceList().get(0),
                        Optional.ofNullable(documentFrequencyPerClass.get(word)
                                .get(document.getPlaceList().get(0)))
                                .orElse(0) + 1);
            });
        });
        classes.forEach(clazz -> {
            double numberOfWordsInClass = termFrequencyPerClass.values()
                    .stream()
                    .mapToInt(map -> Optional.ofNullable(map.get(clazz)).orElse(0))
                    .sum();
            double numberOfDocumentsInClass = documents.stream()
                    .filter(document -> document.getPlaceList().get(0).equals(clazz))
                    .count();
            termFrequencyPerDocument.keySet().forEach(word -> {
                if (termFrequencyPerClass.get(word).get(clazz) != null) {
                    double tf = termFrequencyPerClass.get(word).get(clazz) / numberOfWordsInClass;
                    double df = documentFrequencyPerClass.get(word)
                            .get(clazz) / numberOfDocumentsInClass;
                    double tfidf = tf * (1.0 / df);
                    if (tfidf > scores.get(word)) {
                        scores.put(word, tfidf);
                    }
                }
            });
        });
    }

    private void calculateScoresUsingTFIDF() {
        if (termFrequencyPerDocument.isEmpty()) {
            throw new NoOccurrencesException();
        }
        termFrequencyPerDocument.keySet().forEach(word -> scores.put(word, Double.MIN_VALUE));

        termFrequencyPerDocument.keySet().forEach((word) -> {
            Map<Document, Integer> occurrence = termFrequencyPerDocument.get(word);
            occurrence.keySet().forEach((document) -> {
                double tf = termFrequencyPerDocument.get(word)
                        .get(document) / (double) document.getWordList().size();
                double df = termFrequencyPerDocument.get(word)
                        .keySet()
                        .size() / (double) documents.size();
                double tfidf = tf * (1.0 / df);
                if (tfidf > scores.get(word)) {
                    scores.put(word, tfidf);
                }
            });
        });
    }

    private Set<String> retrieveKeywords(int numberOfKeywords) {
        return scores.keySet().stream().sorted((String word1, String word2) -> {
            if (scores.get(word1) < scores.get(word2)) {
                return -1;
            } else if (scores.get(word1) > scores.get(word2)) {
                return 1;
            } else {
                return 0;
            }
        }).limit(numberOfKeywords).collect(Collectors.toSet());
    }

    public Set<String> getKeywordsByTFIDF(int numberOfKeywords) {
        calculateScoresUsingTFIDF();
        return retrieveKeywords(numberOfKeywords);
    }

    public Set<String> getKeywordsByClassTFIDF(int numberOfKeywords) {
        calculateScoresUsingClassTFIDF();
        return retrieveKeywords(numberOfKeywords);
    }

    public Set<String> getKeywordsByClassTF(int numberOfKeywords) {
        calculateScoresUsingClassTF();
        return retrieveKeywords(numberOfKeywords);
    }
}
