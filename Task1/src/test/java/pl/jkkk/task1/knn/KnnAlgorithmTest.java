package pl.jkkk.task1.knn;

import org.junit.jupiter.api.Test;
import pl.jkkk.task1.exception.MetricNotSupportedException;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.Metric;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnnAlgorithmTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private KnnAlgorithm knnAlgorithm = new KnnAlgorithm();

    private FeatureVector testVector = new FeatureVector(1.0, 2.0, 7.9);
    private List<FeatureVector> trainingVectors = Stream.of(
            new FeatureVector(12.0, 22.0, 15.0),
            new FeatureVector(17.0, 17.0, 12.0),
            new FeatureVector(22.0, 0.0, 8.0),
            new FeatureVector(4.0, -5.0, 7.5),
            new FeatureVector(8.0, -15.0, 12.8),
            new FeatureVector(2.0, 3.0, 4.9),
            new FeatureVector(5.0, 7.0, 15.9)
    ).collect(Collectors.toCollection(ArrayList::new));

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    void calculateTest() throws MetricNotSupportedException {
        List<FeatureVector> correctVectors = Stream.of(
                new FeatureVector(2.0, 3.0, 4.9),
                new FeatureVector(4.0, -5.0, 7.5),
                new FeatureVector(5.0, 7.0, 15.9)
        ).collect(Collectors.toCollection(ArrayList::new));

        List<CalculatedFeatureVector> resultVectors = knnAlgorithm.calculate(
                testVector, trainingVectors, 3, Metric.EUCLIDEAN);

        for (int i = 0; i < correctVectors.size(); i++) {
            for (int j = 0; j < correctVectors.get(i).size(); j++) {
                assertEquals(correctVectors.get(i).get(j),
                        resultVectors.get(i).getFeatureVector().get(j));
            }
        }
    }
}
