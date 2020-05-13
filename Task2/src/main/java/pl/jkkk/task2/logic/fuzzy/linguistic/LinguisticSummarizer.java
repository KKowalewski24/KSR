package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.function.Function;

public class LinguisticSummarizer<T> {
    
    private final Function<T,Double> converter;
    private final Label label;

    public LinguisticSummarizer(Function<T,Double> converter, Label label) {
        this.converter = converter;
        this.label = label;
    }

    public double compatibilityLevel(T object) {
        return label.compatibilityLevel(converter.apply(object));
    }
}
