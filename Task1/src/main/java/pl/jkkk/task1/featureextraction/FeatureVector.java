package pl.jkkk.task1.featureextraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.jkkk.task1.model.Document;

public class FeatureVector extends ArrayList<Feature> {

    /*------------------------ FIELDS REGION ------------------------*/

    public static final double DEFAULT_VALUE = 0.0;

    private final Document document;

    /*------------------------ METHODS REGION ------------------------*/

    public FeatureVector(final Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    /** 
     * Normalize numerical parts of provided feature vectors
     */
    public static void normalize(List<FeatureVector> vectors) {
        double[] maxs = new double[vectors.get(0).size()];
        for(int i = 0; i < vectors.get(0).size(); i++){
            if(vectors.get(0).get(i).isNumber()){
                final int index = i;
                maxs[i] = vectors.stream()
                    .mapToDouble(vector -> vector.get(index).getNumber())
                    .max().getAsDouble();
            }
        }
        for(FeatureVector vector : vectors) {
            for(int i = 0; i < vector.size(); i++) {
                if(vector.get(i).isNumber()) {
                    vector.get(i).setNumber(vector.get(i).getNumber() / maxs[i]);
                }
            }
        }
    }

    /**
     * Calculate euclidean distance between numerical vectors
     */
    private static Double calculateEuclidean(List<Double> a, List<Double> b) {
        double result = 0.0;
        for (int i = 0; i < a.size(); i++) {
            result += Math.pow(a.get(i) - b.get(i), 2);
        }
        return Math.sqrt(result);
    }

    /**
     * Calculate manhattan distance between numerical vectors
     */
    private static Double calculateManhattan(List<Double> a, List<Double> b) {
        double result = 0.0;
        for (int i = 0; i < a.size(); i++) {
            result += Math.abs(a.get(i) - b.get(i));
        }
        return result;
    }

    /**
     * Calculate chebyshev distance beetween numerical vectors
     */
    private static Double calculateChebyshev(List<Double> a, List<Double> b) {
        List<Double> maxs = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            maxs.add(Math.abs(a.get(i) - b.get(i)));
        }
        return Collections.max(maxs);
    }

    /**
     * Calculate trigram distance between two strings
     */
    public static Double calculateTrigramDistance(String a, String b){
        //TODO
        return 0.0;
    }

    /**
     * Calculate tfm distance between two strings
     */
    public static Double calculateTFMDistance(String a, String b){
        if(a.equals(b))
            return 1.0;
        else
            return 0.0;
    }

    /**
     * Calculate distance between feature vectors using provided
     * text metric and numerical metric
     */
    public static Double calculateDistance(FeatureVector a, FeatureVector b,
                    NumericalMetric numericalMetric, TextMetric textMetric) {

        /* Convert feature vectors to numercial vectors using selected text metric.
         * Text values from first vector are set to 0.0, text values from second
         * vector are set to computed distance to parallel values from first vector. */
        List<Double> numericalA = new ArrayList<>();
        List<Double> numericalB = new ArrayList<>();
        for(int i = 0; i < a.size(); i++){
            if(a.get(i).isNumber()){
                numericalA.add(a.get(i).getNumber());
                numericalB.add(b.get(i).getNumber());
            }else{
                double distance;
                switch (textMetric) {
                    case TFM:
                        distance = calculateTFMDistance(a.get(i).getText(), b.get(i).getText());
                        break;
                    default:
                        distance = calculateTrigramDistance(a.get(i).getText(), b.get(i).getText());
                }
                numericalA.add(0.0);
                numericalB.add(distance);
            }
        }

        /* Calculate distance between numerical vectors using selected numerical metric */
        switch (numericalMetric) {
            case EUCLIDEAN: {
                return calculateEuclidean(numericalA, numericalB);
            }
            case MANHATTAN: {
                return calculateManhattan(numericalA, numericalB);
            }
            default /*CHEBYSHEV*/: {
                return calculateChebyshev(numericalA, numericalB);
            }
        }
    }
}
