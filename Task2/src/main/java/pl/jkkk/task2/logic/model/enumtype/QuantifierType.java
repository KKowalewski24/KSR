package pl.jkkk.task2.logic.model.enumtype;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuantifierType {

    /*------------------------ FIELDS REGION ------------------------*/
    RELATIVE("Relative"),
    ABSOLUTE("Absolute");

    private final String name;

    /*------------------------ METHODS REGION ------------------------*/
    QuantifierType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static QuantifierType fromString(final String text) {
        return Arrays.stream(QuantifierType.values())
                .filter((it) -> it.getName().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> getNamesList() {
        return Arrays.stream(QuantifierType.values())
                .map(QuantifierType::getName)
                .collect(Collectors.toList());
    }
}
