package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.List;

public class LinguisticSummary<T> {

    private final LinguisticQuantifier quantifier;
    private final LinguisticSummarizer<T> summarizer;
    private final List<T> objects;

    public LinguisticSummary(LinguisticQuantifier quantifier,
            LinguisticSummarizer<T> summarizer, List<T> objects) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.objects = objects;
    }

    public double degreeOfTruth() {
        /* the crucial point !!! */
        return 0.0;
    }
}
