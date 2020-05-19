package pl.jkkk.task2.logic.fuzzy.linguistic;

import java.io.Serializable;

/**
 * This class represents linguistic variable, it has some name
 * and provides method for retrieving some attribute's value from object.
 */
public abstract class LinguisticVariable<T> implements Serializable {

    private final String name;

    public LinguisticVariable(final String name) {
        this.name = name;
    }

    public abstract Double extractAttribute(T object);

    public String getName() {
        return name;
    }
}
