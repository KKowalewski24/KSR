package pl.jkkk.task2.logic.fuzzy.set;

import java.io.Serializable;

/**
 * This interface represents fuzzy set with a specific membership function
 */
public interface FuzzySet extends Serializable {
    
    /**
     * This method returns degree of membership of an object to the fuzzy set,
     * this is just specific memership function implementation
     */
    abstract double contains(double x);
}
