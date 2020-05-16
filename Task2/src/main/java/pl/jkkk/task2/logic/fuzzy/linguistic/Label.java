package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.UnionFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.IntersectionFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.ComplementFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.ModifiedFuzzySet;

public class Label<T> {

    private String name;
    private FuzzySet fuzzySet;
    private LinguisticVariable<T> linguisticVariable;

    public Label() {
    }

    public Label(final String name, final FuzzySet fuzzySet,
                 final LinguisticVariable<T> linguisticVariable) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.linguisticVariable = linguisticVariable;
    }

    public double compatibilityLevel(final T object) {
        return fuzzySet.contains(linguisticVariable.getExtractor().apply(object));
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

    public LinguisticVariable<T> getLinguisticVariable() {
        return linguisticVariable;
    }

    public void setLinguisticVariable(final LinguisticVariable<T> linguisticVariable) {
        this.linguisticVariable = linguisticVariable;
    }

    public Label<T> and(final Label<T> label) {
        return new Label<>(name + " and " + label.name,
                new IntersectionFuzzySet(fuzzySet, label.fuzzySet), linguisticVariable);
    }

    public Label<T> or(final Label<T> label) {
        return new Label<>(name + " or " + label.name,
                new UnionFuzzySet(fuzzySet, label.fuzzySet), linguisticVariable);
    }

    public Label<T> not() {
        return new Label<>("not " + name,
                new ComplementFuzzySet(fuzzySet), linguisticVariable);
    }

    public Label<T> modify(final Modifier modifier) {
        return new Label<>(modifier.getText() + " " + name, 
                new ModifiedFuzzySet(fuzzySet, modifier.getR()), linguisticVariable);
    }
}
