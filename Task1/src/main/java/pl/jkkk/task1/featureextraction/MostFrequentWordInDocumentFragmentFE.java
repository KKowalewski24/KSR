package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentWordInDocumentFragmentFE implements FeatureExtractor {

    public static final String ABBREVIATION = "mfwidf";

    private final int rangeBeginInPercents;
    private final int rangeEndInPercents;

    public MostFrequentWordInDocumentFragmentFE(int rangeBeginInPercents, int rangeEndInPercents) {
        this.rangeBeginInPercents = rangeBeginInPercents;
        this.rangeEndInPercents = rangeEndInPercents;
    }

    @Override
    public FeatureVector extract(Document document) {
        FeatureVector vector = new FeatureVector(document);
        int rangeBegin = rangeBeginInPercents * document.getWordList().size() / 100;
        int rangeEnd = rangeEndInPercents * document.getWordList().size() / 100;
        Map<String, Integer> termFrequency = new HashMap<>();
        document.getWordList().stream()
                .skip(rangeBegin)
                .limit(rangeEnd - rangeBegin)
                .forEach(word -> {
                    termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
                });
        vector.add(new Feature(termFrequency.keySet().stream()
                .sorted((word1, word2) -> termFrequency.get(word1) - termFrequency.get(word2))
                .findFirst()
                .orElse("")));
        return vector;
    }
}
