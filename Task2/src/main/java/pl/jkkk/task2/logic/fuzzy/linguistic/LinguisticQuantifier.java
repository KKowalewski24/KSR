package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.io.Serializable;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.ModifiedFuzzySet;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;

public class LinguisticQuantifier implements Serializable {

    private final String name;
    private final FuzzySet fuzzySet;
    private final QuantifierType quantifierType;

    public LinguisticQuantifier(String name, FuzzySet fuzzySet, QuantifierType quantifierType) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.quantifierType = quantifierType;
    }

    public double compatibilityLevel(double x) {
        return fuzzySet.contains(x);
    }

    public String getName() {
        return name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public QuantifierType getQuantifierType() {
        return quantifierType;
    }

    public LinguisticQuantifier modify(final Modifier modifier) {
        return new LinguisticQuantifier(modifier.getText() + " " + name,
                new ModifiedFuzzySet(fuzzySet, modifier.getR()), quantifierType);
    }
}
