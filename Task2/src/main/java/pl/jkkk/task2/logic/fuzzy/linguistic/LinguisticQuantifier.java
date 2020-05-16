package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;

public class LinguisticQuantifier {

    private String name;
    private FuzzySet fuzzySet;

    public LinguisticQuantifier() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public void setFuzzySet(final FuzzySet fuzzySet) {
        this.fuzzySet = fuzzySet;
    }
}
