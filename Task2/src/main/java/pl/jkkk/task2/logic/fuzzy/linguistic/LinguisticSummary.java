package pl.jkkk.task2.logic.fuzzy.linguistic;

import pl.jkkk.task2.logic.model.enumtype.QuantifierType;

import java.util.Set;

public class LinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final Label label;
    private Set<T> objects;

    public LinguisticSummary(LinguisticQuantifier quantifier, Label<T> label, Set<T> objects) {
        this.quantifier = quantifier;
        this.label = label;
        this.objects = objects;
    }

    public LinguisticQuantifier getQuantifier() {
        return quantifier;
    }

    public Label getLabel() {
        return label;
    }

    public Set<T> getObjects() {
        return objects;
    }

    public double degreeOfTruth() {
        Set<Double> universeOfDiscourse = label.getLinguisticVariable()
                .universeOfDiscourse(objects);
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            return quantifier.compatibilityLevel(label.getFuzzySet()
                    .cardinality(universeOfDiscourse));
        } else {
            return quantifier.compatibilityLevel(
                    label.getFuzzySet().cardinality(universeOfDiscourse) / objects.size());
        }
    }

    @Override
    public String toString() {
        return quantifier.getName() + " measurement " + label.getName();
    }
}
