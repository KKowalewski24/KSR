package pl.jkkk.task1.featureextraction;

import java.util.Set;

import pl.jkkk.task1.model.Document;

public class RelativeNumberOfKeywordsFE extends NumberOfKeywordsFE{

    public RelativeNumberOfKeywordsFE(final Set<String> keywords){
        super(keywords);
    }

    @Override
    public FeatureVector extract(Document document){
        FeatureVector vector = super.extract(document);
        vector.set(0, vector.get(0) / document.getWordList().size());
        return vector;
    }

}

