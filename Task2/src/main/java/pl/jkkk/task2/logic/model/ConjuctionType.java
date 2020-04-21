package pl.jkkk.task2.logic.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ConjuctionType {
    AND("and"),
    OR("or");

    private final String name;

    ConjuctionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ConjuctionType fromString(String text) {
        return Arrays.asList(ConjuctionType.values())
                .stream()
                .filter((it) -> it.getName().equals(text))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> getNamesList() {
        return Arrays.asList(ConjuctionType.values())
                .stream()
                .map((it) -> it.getName())
                .collect(Collectors.toList());
    }
}
