package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.model.Document;

public interface FeatureExtractor {
    FeatureVector extract(Document document);
}
