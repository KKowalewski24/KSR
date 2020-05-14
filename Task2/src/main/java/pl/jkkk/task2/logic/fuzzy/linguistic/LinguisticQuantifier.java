package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class LinguisticQuantifier extends BaseEntity {

    private String name;
    //    TODO REMOVE THIS
    @Transient
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
