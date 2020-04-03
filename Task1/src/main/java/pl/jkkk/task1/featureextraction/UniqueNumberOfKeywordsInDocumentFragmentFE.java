package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

import java.util.Set;
import java.util.stream.Collectors;

public class UniqueNumberOfKeywordsInDocumentFragmentFE implements FeatureExtractor {

    public static final String ABBREVIATION = "unkidf";

    private final Set<String> keywords;
    private final int rangeBeginInPercents;
    private final int rangeEndInPercents;

    public UniqueNumberOfKeywordsInDocumentFragmentFE(Set<String> keywords,
                                                      int rangeBeginInPercents,
                                                      int rangeEndInPercents) {
        this.keywords = keywords;
        this.rangeBeginInPercents = rangeBeginInPercents;
        this.rangeEndInPercents = rangeEndInPercents;
    }

    @Override
    public FeatureVector extract(Document document) {
        FeatureVector vector = new FeatureVector(document);
        int rangeBegin = rangeBeginInPercents * document.getWordList().size() / 100;
        int rangeEnd = rangeEndInPercents * document.getWordList().size() / 100;
        vector.add(new Feature((double) document.getWordList().stream()
                .skip(rangeBegin)
                .limit(rangeEnd - rangeBegin)
                .filter((word) -> keywords.contains(word))
                .collect(Collectors.toSet())
                .size()));

        return vector;
    }
}
