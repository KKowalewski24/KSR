package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.List;

import pl.jkkk.task2.logic.model.enumtype.QuantifierType;

public class LinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final Label<T> qualifier;
    private final Label<T> summarizer;
    private List<T> objects;

    public LinguisticSummary(LinguisticQuantifier quantifier, Label<T> summarizer, List<T> objects) {
        this.quantifier = quantifier;
        this.qualifier = null;
        this.summarizer = summarizer;
        this.objects = objects;
    }

    public double degreeOfTruth() {
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            return quantifier.compatibilityLevel(summarizer.getFuzzySet().cardinality(objects));
        } else {
            return quantifier.compatibilityLevel(summarizer.getFuzzySet().cardinality(objects) / objects.size());
        }
    }

    @Override
    public String toString() {
        return quantifier.getName() + " measurements " + summarizer.getName();
    }
}
