package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.HashSet;
import java.util.Set;

public class LinguisticVariable {
    
    private final String name;
    private final Set<Label> labels;

    public LinguisticVariable(String name) {
        this.name = name;
        this.labels = new HashSet<>();
    }
}
