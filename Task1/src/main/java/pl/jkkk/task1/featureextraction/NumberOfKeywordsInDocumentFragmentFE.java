package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

import java.util.Set;
import java.util.stream.Collectors;

public class NumberOfKeywordsInDocumentFragmentFE implements FeatureExtractor {

    private final Set<String> keywords;
    private final int rangeBeginInPercents;
    private final int rangeEndInPercents;

    public NumberOfKeywordsInDocumentFragmentFE(Set<String> keywords, int rangeBeginInPercents,
                                                int rangeEndInPercents) {
        this.keywords = keywords;
        this.rangeBeginInPercents = rangeBeginInPercents;
        this.rangeEndInPercents = rangeEndInPercents;
    }

    @Override
    public FeatureVector extract(Document document) {
        FeatureVector vector = new FeatureVector();
        int rangeBegin = rangeBeginInPercents * document.getWordList().size() / 100;
        int rangeEnd = rangeEndInPercents * document.getWordList().size() / 100;
        vector.add((double) document.getWordList().stream()
                .skip(rangeBegin)
                .limit(rangeEnd - rangeBegin)
                .filter((word) -> keywords.contains(word))
                .collect(Collectors.toList())
                .size());

        return vector;
    }
}
