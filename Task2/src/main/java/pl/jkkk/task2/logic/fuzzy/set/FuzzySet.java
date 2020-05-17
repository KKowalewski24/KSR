package pl.jkkk.task2.logic.fuzzy.set;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents fuzzy set with a specific membership function,
 * universe of discourse is 'undefined' (all real numbers), it can be
 * defined (discrete, finite subset of R) when computing cardinality,
 * support and alpha-cut.
 */
public abstract class FuzzySet implements Serializable {

    /**
     * This method returns degree of membership of an object to the fuzzy set,
     * this is just specific memership function implementation
     */
    public abstract double contains(double x);

    /**
     * Compute cardinality in discrete, finite universe of discourse X
     */
    public double cardinality(List<Double> X) {
        return X.stream().mapToDouble(x -> contains(x)).sum();
    }

    /**
     * Compute support in discrete, finite universe of discourse X
     */
    public List<Double> support(List<Double> X) {
        return X.stream().filter(x -> contains(x) > 0.0).collect(Collectors.toList());
    }

    /**
     * Compute alpha-cut in discrete, finite universe of discourse X
     */
    public List<Double> alphacut(List<Double> X, double a) {
        return X.stream().filter(x -> contains(x) >= a).collect(Collectors.toList());
    }
}
