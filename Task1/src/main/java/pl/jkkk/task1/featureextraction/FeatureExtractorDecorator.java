package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

import java.util.ArrayList;
import java.util.List;

public class FeatureExtractorDecorator implements FeatureExtractor {

    private List<FeatureExtractor> extractors = new ArrayList<>();

    @Override
    public FeatureVector extract(final Document document) {
        FeatureVector featureVector = new FeatureVector(document);
        extractors.stream()
                .forEach((extractor) -> featureVector.addAll(extractor.extract(document)));

        return featureVector;
    }

    public void addExtractor(final FeatureExtractor extractor) {
        extractors.add(extractor);
    }
}
