package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

import java.util.Set;

public class RelativeNumberOfKeywordsInDocumentFragmentFE
        extends NumberOfKeywordsInDocumentFragmentFE {

    public RelativeNumberOfKeywordsInDocumentFragmentFE(final Set<String> keywords,
                                                        int rangeBeginInPercents,
                                                        int rangeEndInPercents) {
        super(keywords, rangeBeginInPercents, rangeEndInPercents);
    }

    @Override
    public FeatureVector extract(Document document) {
        FeatureVector vector = super.extract(document);
        vector.get(0).setNumber(vector.get(0).getNumber() / document.getWordList().size());
        return vector;
    }

}

