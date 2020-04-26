package pl.jkkk.task2.logic.model.enumtype;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QualifierSummarizationType {
    //    TODO ADD TYPES
    ;

    private final String name;

    QualifierSummarizationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static QualifierSummarizationType fromString(String text) {
        return Arrays.asList(QualifierSummarizationType.values())
                .stream()
                .filter((it) -> it.getName().equals(text))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> getNamesList() {
        return Arrays.asList(QualifierSummarizationType.values())
                .stream()
                .map((it) -> it.getName())
                .collect(Collectors.toList());
    }
}
