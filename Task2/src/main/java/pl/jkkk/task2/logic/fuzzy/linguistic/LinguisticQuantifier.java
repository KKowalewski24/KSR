package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.io.Serializable;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;

public class LinguisticQuantifier implements Serializable {

    private Long id;
    private final String name;
    private final FuzzySet<Double> fuzzySet;
    private final QuantifierType quantifierType;

    public LinguisticQuantifier(String name, FuzzySet<Double> fuzzySet, QuantifierType quantifierType) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.quantifierType = quantifierType;
    }

    public double compatibilityLevel(double x) {
        return fuzzySet.contains(x);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public FuzzySet<Double> getFuzzySet() {
        return fuzzySet;
    }

    public QuantifierType getQuantifierType() {
        return quantifierType;
    }
}
