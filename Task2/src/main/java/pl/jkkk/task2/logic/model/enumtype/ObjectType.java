package pl.jkkk.task2.logic.model.enumtype;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ObjectType {

    /*------------------------ FIELDS REGION ------------------------*/
    QUANTIFIER("Quantifier"),
    SUMMARIZER("Summarizer");

    private final String name;

    /*------------------------ METHODS REGION ------------------------*/
    ObjectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ObjectType fromString(final String text) {
        return Arrays.asList(ObjectType.values())
                .stream()
                .filter((it) -> it.getName().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> getNamesList() {
        return Arrays.asList(ObjectType.values())
                .stream()
                .map((it) -> it.getName())
                .collect(Collectors.toList());
    }
}
