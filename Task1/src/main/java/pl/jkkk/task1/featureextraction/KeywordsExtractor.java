package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.exception.NoDocumentsException;
import pl.jkkk.task1.exception.NoOccurrencesException;
import pl.jkkk.task1.model.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class KeywordsExtractor {

    /*------------------------ FIELDS REGION ------------------------*/
    /* Occurrences per documents for each word */
    private Map<String, Map<Document, Integer>> occurrences;

    /* Score (from some algorithm) for each word */
    private Map<String, Double> scores;

    /* All processed documents */
    private List<Document> documents;

    /*------------------------ METHODS REGION ------------------------*/
    public KeywordsExtractor(final List<Document> documents) {
        this.occurrences = new HashMap<>();
        this.scores = new HashMap<>();
        this.documents = documents;
    }

    /**
     * Calculate occurrences per document for each word from documents' list,
     * must be called before any keywords retrieving method.
     * param documents list of documents
     */
    public void calculateOccurrences() {
        if (documents.isEmpty()) {
            throw new NoDocumentsException();
        }

        occurrences.clear();

        documents.forEach((document) -> {
            document.getWordList().forEach((word) -> {
                if (!occurrences.containsKey(word)) {
                    occurrences.put(word, new HashMap<>());
                }

                Map<Document, Integer> occurrence = occurrences.get(word);
                occurrence.put(document,
                        Optional.ofNullable(occurrence.get(document)).orElse(0) + 1);
            });
        });
    }

    private void calculateScoresUsingTFIDF() {
        if (occurrences.isEmpty()) {
            throw new NoOccurrencesException();
        }

        scores.clear();

        occurrences.keySet().forEach((word) -> {
            Map<Document, Integer> occurrence = occurrences.get(word);
            occurrence.keySet().forEach((document) -> {
                double tf = occurrence.get(document)
                        / (double) document.getWordList().size();
                double idf = documents.size()
                        / (double) occurrence.keySet().size();
                scores.put(word,
                        Optional.ofNullable(scores.get(word)).orElse(0.0) + tf * idf);
            });
        });
    }

    /**
     * Calculate scores using Terms Per Squared Documents' number method.
     */
    private void calculateScoresUsingTPSD() {
        if (occurrences.isEmpty()) {
            throw new NoOccurrencesException();
        }

        scores.clear();

        occurrences.keySet().forEach((word) -> {
            double sum = occurrences.get(word).values().stream()
                    .mapToDouble((value) -> value).sum();
            double count = occurrences.get(word).values().size();
            scores.put(word, sum / (count * count));
        });
    }

    private Set<String> retrieveKeywords(int numberOfKeywords) {
        return scores.keySet().stream()
                .sorted((String word1, String word2) -> {
                    if (scores.get(word1) < scores.get(word2)) {
                        return -1;
                    } else if (scores.get(word1) > scores.get(word2)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .limit(numberOfKeywords)
                .collect(Collectors.toSet());
    }

    /**
     * Create list of keywords using modified TF-IDF algorithm.
     *
     * @param numberOfKeywords number of keywords to retrieve
     */
    public Set<String> getKeywordsByTFIDF(int numberOfKeywords) {
        calculateScoresUsingTFIDF();

        return retrieveKeywords(numberOfKeywords);
    }

    /**
     * Create list of keywords using Terms Per Squared Document's number algorithm.
     *
     * @param numberOfKeywords number of keywords to retrieve
     */
    public Set<String> getKeywordsByTPSD(int numberOfKeywords) {
        calculateScoresUsingTPSD();

        return retrieveKeywords(numberOfKeywords);
    }
}
