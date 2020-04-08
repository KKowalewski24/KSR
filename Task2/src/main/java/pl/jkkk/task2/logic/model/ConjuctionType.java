package pl.jkkk.task2.logic.model;

import java.util.Arrays;

public enum ConjuctionType {
    AND("and"),
    OR("or");

    public final String name;

    ConjuctionType(String name) {
        this.name = name;
    }

    public static ConjuctionType fromString(String text) {
        return Arrays.asList(ConjuctionType.values())
                .stream()
                .filter((it) -> it.name.equals(text))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
