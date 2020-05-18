package pl.jkkk.task2.logic.fuzzy.set;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents fuzzy set with a specific membership function,
 * universe of discourse consists of all possible objects of type T, it can
 * be limited to some discrete, finite set of this objects when computing cardinality,
 * support and alpha-cut.
 */
public abstract class FuzzySet<T> implements Serializable {

    /**
     * This method returns degree of membership of an object to the fuzzy set.
     *
     * @param x object
     * @return degree of membership
     */
    public abstract double contains(T x);

    /**
     * Compute cardinality in discrete, finite universe of discourse X
     */
    public double cardinality(List<T> X) {
        return X.stream().mapToDouble(this::contains).sum();
    }

    /**
     * Compute support in discrete, finite universe of discourse X
     */
    public List<T> support(List<T> X) {
        return X.stream().filter(x -> contains(x) > 0.0).collect(Collectors.toList());
    }

    /**
     * Compute alpha-cut in discrete, finite universe of discourse X
     */
    public List<T> alphacut(List<T> X, double a) {
        return X.stream().filter(x -> contains(x) >= a).collect(Collectors.toList());
    }
}
