package pl.jkkk.task1.knn;

import pl.jkkk.task1.exception.MetricNotSupportedException;
import pl.jkkk.task1.featureextraction.FeatureVector;
import pl.jkkk.task1.featureextraction.Metric;
import pl.jkkk.task1.model.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.jkkk.task1.constant.Constants.PLACES_CANADA;
import static pl.jkkk.task1.constant.Constants.PLACES_FRANCE;
import static pl.jkkk.task1.constant.Constants.PLACES_JAPAN;
import static pl.jkkk.task1.constant.Constants.PLACES_UK;
import static pl.jkkk.task1.constant.Constants.PLACES_USA;
import static pl.jkkk.task1.constant.Constants.PLACES_WEST_GERMANY;

public class KnnAlgorithm {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    List<FeatureVector> calculate(FeatureVector featureVector, List<FeatureVector> trainingVectors,
                                  int numberK, Metric metric) throws MetricNotSupportedException {
        List<CalculatedFeatureVector> calculatedFeatureVectors = new ArrayList<>();

        for (FeatureVector it : trainingVectors) {
            calculatedFeatureVectors.add(new CalculatedFeatureVector(it,
                    FeatureVector.calculateDistance(featureVector, it, metric)));
        }

        Collections.sort(calculatedFeatureVectors);
        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();

        return calculatedFeatureVectors.stream()
                .map((it) -> it.getFeatureVector())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    String classify(List<Document> documents) {
        Map<String, Integer> placesOccurrences = new HashMap<>();
        placesOccurrences.put(PLACES_WEST_GERMANY, 0);
        placesOccurrences.put(PLACES_USA, 0);
        placesOccurrences.put(PLACES_FRANCE, 0);
        placesOccurrences.put(PLACES_UK, 0);
        placesOccurrences.put(PLACES_CANADA, 0);
        placesOccurrences.put(PLACES_JAPAN, 0);

        documents.forEach((it) -> {
            String place = it.getPlaceList().get(0);
            placesOccurrences.put(place, placesOccurrences.get(place) + 1);
        });

        return placesOccurrences.entrySet()
                .stream()
                .max((val1, val2) -> val1.getValue() > val2.getValue() ? 1 : -1)
                .get()
                .getKey();
    }

    public String calculateAndClassify(FeatureVector featureVector,
                                       List<FeatureVector> trainingVectors, int numberK,
                                       Metric metric) throws MetricNotSupportedException {

        List<FeatureVector> selectedVectors = calculate(featureVector,
                trainingVectors, numberK, metric);

        String classifiedPlace = classify(selectedVectors
                .stream()
                .map((it) -> it.getDocument())
                .collect(Collectors.toCollection(ArrayList::new)));

        return classifiedPlace;
    }
}
    