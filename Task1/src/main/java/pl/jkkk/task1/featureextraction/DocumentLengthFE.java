package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

public class DocumentLengthFE implements FeatureExtractor {

    @Override
    public FeatureVector extract(Document document) {
        FeatureVector vector = new FeatureVector(document);
        vector.add(new Feature((double) document.getWordList().size()));
        return vector;
    }
}
