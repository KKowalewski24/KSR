package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.exception.MetricNotSupportedException;
import pl.jkkk.task1.model.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FeatureVector extends ArrayList<Double> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final double DEFAULT_VALUE = 0.0;

    private Document document;

    /*------------------------ METHODS REGION ------------------------*/
    public FeatureVector(Double... values) {
        super(Arrays.asList(values));
    }

    public FeatureVector(final Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(final Document document) {
        this.document = document;
    }

    public static void normalize(List<FeatureVector> vectors) {
        FeatureVector maxs = new FeatureVector();
        IntStream.range(0, vectors.get(0).size()).forEach(i -> {
            maxs.add(vectors.stream().mapToDouble(vector -> vector.get(i)).max().getAsDouble());
        });
        vectors.forEach(vector -> {
            for (int i = 0; i < vector.size(); i++) {
                vector.set(i, vector.get(i) / maxs.get(i));
            }
        });
    }

    private static Double calculateEuclidean(FeatureVector featureVectorOne,
                                             FeatureVector featureVectorTwo) {
        Double result = new Double(DEFAULT_VALUE);

        for (int i = 0; i < featureVectorOne.size(); i++) {
            result += Math.pow(featureVectorOne.get(i) - featureVectorTwo.get(i), 2);
        }

        return Math.sqrt(result);
    }

    private static Double calculateManhattan(FeatureVector featureVectorOne,
                                             FeatureVector featureVectorTwo) {
        Double result = new Double(DEFAULT_VALUE);

        for (int i = 0; i < featureVectorOne.size(); i++) {
            result += Math.abs(featureVectorOne.get(i) - featureVectorTwo.get(i));
        }

        return result;
    }

    private static Double calculateChebyshev(FeatureVector featureVectorOne,
                                             FeatureVector featureVectorTwo) {
        FeatureVector vector = new FeatureVector();

        for (int i = 0; i < featureVectorOne.size(); i++) {
            vector.add(Math.abs(featureVectorOne.get(i) - featureVectorTwo.get(i)));
        }

        return Collections.max(vector);
    }

    public static Double calculateDistance(FeatureVector featureVectorOne,
                                           FeatureVector featureVectorTwo,
                                           Metric metric) throws MetricNotSupportedException {
        switch (metric) {
            case EUCLIDEAN: {
                return calculateEuclidean(featureVectorOne, featureVectorTwo);
            }
            case MANHATTAN: {
                return calculateManhattan(featureVectorOne, featureVectorTwo);
            }
            case CHEBYSHEV: {
                return calculateChebyshev(featureVectorOne, featureVectorTwo);
            }
            default: {
                throw new MetricNotSupportedException();
            }
        }
    }
}
