package pl.jkkk.task1.knn;

import pl.jkkk.task1.exception.MetricNotSupportedException;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.Metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnnAlgorithm {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public List<CalculatedFeatureVector> calculate(
            FeatureVector featureVector, List<FeatureVector> trainingVectors,
            int numberK, Metric metric) throws MetricNotSupportedException {

        List<CalculatedFeatureVector> calculatedFeatureVectors = new ArrayList<>();

        for (FeatureVector it : trainingVectors) {
            calculatedFeatureVectors.add(new CalculatedFeatureVector(it,
                    FeatureVector.calculateDistance(featureVector, it, metric)));
        }

        Collections.sort(calculatedFeatureVectors);
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();

//        TODO ADD CLASIFICATON TO CERTAIN CLASS

        return calculatedFeatureVectors;
    }
}
    