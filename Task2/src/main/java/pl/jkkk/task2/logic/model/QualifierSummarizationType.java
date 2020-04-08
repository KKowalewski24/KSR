package pl.jkkk.task2.logic.model;

import java.util.Arrays;

public enum QualifierSummarizationType {
    //    TODO ADD TYPES
    ;

    public final String name;

    QualifierSummarizationType(String name) {
        this.name = name;
    }

    public static QualifierSummarizationType fromString(String text) {
        return Arrays.asList(QualifierSummarizationType.values())
                .stream()
                .filter((it) -> it.name.equals(text))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
