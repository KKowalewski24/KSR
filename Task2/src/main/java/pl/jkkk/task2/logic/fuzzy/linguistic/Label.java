package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;

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
    private final FuzzySet<T> fuzzySet;
    private final LinguisticVariable<T> linguisticVariable;

    public Label(final String name, final FuzzySet<T> fuzzySet,
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

    public FuzzySet<T> getFuzzySet() {
        return fuzzySet;
    }

    public LinguisticVariable<T> getLinguisticVariable() {
        return linguisticVariable;
    }
}
