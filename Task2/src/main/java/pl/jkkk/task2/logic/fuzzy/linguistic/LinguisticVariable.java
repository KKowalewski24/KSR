package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.util.Set;
import java.util.function.Function;

/**
 * This class represents linguistic variable, it contains set
 * of labels and provides method to create universe of discourse
 * extracting values of selected attribute of T instances.
 */
public abstract class LinguisticVariable<T> {

    private final String name;
    private final Set<Label<T>> labels;

    public LinguisticVariable(final String name, final Set<Label<T>> labels) {
        this.name = name;
        this.labels = labels;
    }

    public abstract Set<Double> extractAttributes(Set<T> objects);

    public String getName() {
        return name;
    }

    public Set<Label<T>> getLabels() {
        return labels;
    }
}
