package pl.jkkk.task1.featureextraction;

import java.util.Arrays;

public enum TextMetric {
    TFM("tfm"),
    TRIGRAM("trigram");

    private final String abbreviation;

    TextMetric(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static TextMetric fromString(final String text) {
        return Arrays.asList(TextMetric.values()).stream()
                .filter(metric -> metric.abbreviation.equals(text))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
