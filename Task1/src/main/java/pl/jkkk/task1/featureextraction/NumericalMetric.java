package pl.jkkk.task1.featureextraction;

import java.util.Arrays;

public enum NumericalMetric {
    EUCLIDEAN("eucl"),
    MANHATTAN("manh"),
    CHEBYSHEV("cheb");

    public final String abbreviation;

    NumericalMetric(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static NumericalMetric fromString(final String text) {
        return Arrays.asList(NumericalMetric.values()).stream()
                .filter(metric -> metric.abbreviation.equals(text))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
