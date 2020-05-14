package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.List;

public class LinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final Label<T> summarizer;
    private final List<T> objects;

    public LinguisticSummary(final LinguisticQuantifier quantifier, final Label<T> summarizer, final List<T> objects) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.objects = objects;
    }

    public double degreeOfTruth() {
        /* the crucial point !!! */
        return 0.0;
    }
}
