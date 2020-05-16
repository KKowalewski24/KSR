package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.io.Serializable;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.ModifiedFuzzySet;

public class LinguisticQuantifier implements Serializable {

    public enum Type {
        ABSOLUTE, RELATIVE
    };

    private final String name;
    private final FuzzySet fuzzySet;
    private final Type type;

    public LinguisticQuantifier(String name, FuzzySet fuzzySet, Type type) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.type = type;
    }

    public double compatibilityLevel(double x) {
        return fuzzySet.contains(x);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public LinguisticQuantifier modify(final Modifier modifier) {
        return new LinguisticQuantifier(modifier.getText() + " " + name, 
                new ModifiedFuzzySet(fuzzySet, modifier.getR()), type);
    }
}
