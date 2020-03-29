package pl.jkkk.task1.featureextraction;

import java.util.Arrays;

import pl.jkkk.task1.exception.TypeOfClassificationNotSupportedException;

public enum TypeOfClassification {
    FEATURES("features"),
    TFM("tfm"),
    NGRAM("ngram");

    private final String abbreviation;

    TypeOfClassification(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static TypeOfClassification fromString(String text) throws TypeOfClassificationNotSupportedException {
        return Arrays.asList(TypeOfClassification.values()).stream()
            .filter(type -> type.abbreviation.equals(text))
            .findAny()
            .orElseThrow(TypeOfClassificationNotSupportedException::new);
    }
}
