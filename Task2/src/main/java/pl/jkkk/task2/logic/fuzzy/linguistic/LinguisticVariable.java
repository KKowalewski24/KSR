package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.Set;
import java.util.function.Function;

public class LinguisticVariable<T> {

    private String name;
    private Set<Label<T>> labels;

    public LinguisticVariable() {
    }

    public LinguisticVariable(final String name, final Set<Label<T>> labels, final Function<T, Double> extractor) {
        this.name = name;
        this.labels = labels;
        this.extractor = extractor;
    }

    private Function<T, Double> extractor;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Label<T>> getLabels() {
        return labels;
    }

    public void setLabels(final Set<Label<T>> labels) {
        this.labels = labels;
    }

    public Function<T, Double> getExtractor() {
        return extractor;
    }

    public void setExtractor(final Function<T, Double> extractor) {
        this.extractor = extractor;
    }
}
