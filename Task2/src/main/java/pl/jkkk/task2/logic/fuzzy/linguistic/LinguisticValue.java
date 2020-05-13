package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;

public class LinguisticValue {
    
    private final String name;
    private final FuzzySet fuzzySet;

    public LinguisticValue(String name, FuzzySet fuzzySet) {
        this.name = name;
        this.fuzzySet = fuzzySet;
    }

    public String getName() {
        return name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public double compatibilityLevel(double x) {
        return fuzzySet.contains(x);
    }

    public LinguisticValue modify(Modifier modifier) {
        return new LinguisticValue(modifier.getText() + " " + name, 
                new FuzzySet((x) -> {
                    return Math.pow(x, modifier.getR());
                }));
    }
}
