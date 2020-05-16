package pl.jkkk.task2.logic.model.enumtype;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FunctionType {

    /*------------------------ FIELDS REGION ------------------------*/
    TRAPEZOIDAL("Trapezoidal"),
    TRIANGULAR("Triangular"),
    GAUSSIAN("Gaussian");

    private final String name;

    /*------------------------ METHODS REGION ------------------------*/
    FunctionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static FunctionType fromString(final String text) {
        return Arrays.asList(FunctionType.values())
                .stream()
                .filter((it) -> it.getName().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> getNamesList() {
        return Arrays.asList(FunctionType.values())
                .stream()
                .map((it) -> it.getName())
                .collect(Collectors.toList());
    }
}
