package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents linguistic variable, it has some name
 * and provides universe of discourse for fuzzy sets stored in
 * related labels, this universe is computed by extracting
 * values of selected attribute of class T instances so child
 * classes have to implement mapping method.
 */
public abstract class LinguisticVariable<T> implements Serializable {

    private final String name;

    public LinguisticVariable(final String name) {
        this.name = name;
    }

    public Set<Double> universeOfDiscourse(Set<T> objects) {
        return objects.stream().map(object -> extractAttribute(object)).collect(Collectors.toSet());
    }

    abstract protected Double extractAttribute(T object);

    public String getName() {
        return name;
    }
}
