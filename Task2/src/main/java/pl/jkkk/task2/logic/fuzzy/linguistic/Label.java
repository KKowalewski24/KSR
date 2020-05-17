package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.ComplementFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.IntersectionFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.ModifiedFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.UnionFuzzySet;

import java.io.Serializable;

/**
 * This class represents label of specific linguistic variable,
 * it has some name and store fuzzy set, which can be used to
 * calculate compatibility level of some T object's attribute value
 * with this label.
 */
public class Label<T> implements Serializable {

    private Long id;
    private final String name;
    private final FuzzySet fuzzySet;
    private final LinguisticVariable<T> linguisticVariable;

    public Label(final String name, final FuzzySet fuzzySet,
                 final LinguisticVariable<T> linguisticVariable) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.linguisticVariable = linguisticVariable;
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

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public LinguisticVariable<T> getLinguisticVariable() {
        return linguisticVariable;
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
