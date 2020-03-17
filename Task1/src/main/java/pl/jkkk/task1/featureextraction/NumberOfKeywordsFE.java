package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

import java.util.Set;
import java.util.stream.Collectors;

public class NumberOfKeywordsFE implements FeatureExtractor {

    /*------------------------ FIELDS REGION ------------------------*/
    private final Set<String> keywords;

    /*------------------------ METHODS REGION ------------------------*/
    public NumberOfKeywordsFE(final Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public FeatureVector extract(Document document) {
        FeatureVector vector = new FeatureVector();

        vector.add((double) document.getWordList().stream()
                .filter((word) -> keywords.contains(word))
                .collect(Collectors.toList())
                .size());

        return vector;
    }
}

