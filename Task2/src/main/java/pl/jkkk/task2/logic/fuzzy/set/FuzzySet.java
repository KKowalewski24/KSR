package pl.jkkk.task2.logic.fuzzy.set;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This interface represents fuzzy set defined by a specific membership function,
 * universe of discourse consists of all possible objects of type T, it can
 * be limited to some discrete, finite set of this objects when computing cardinality,
 * support, alpha-cut and degree of fuzziness.
 */
@FunctionalInterface
public interface FuzzySet<T> extends Serializable {

    /**
     * This method returns degree of membership of an object to the fuzzy set.
     *
     * @param x object
     * @return degree of membership
     */
    double contains(T x);

    /**
     * Compute cardinality in discrete, finite universe of discourse X
     */
    default double cardinality(List<T> X) {
        return X.stream().mapToDouble(this::contains).sum();
    }

    /**
     * Compute support in discrete, finite universe of discourse X
     */
    default List<T> support(List<T> X) {
        return X.stream().filter(x -> contains(x) > 0.0).collect(Collectors.toList());
    }

    /**
     * Compute alpha-cut in discrete, finite universe of discourse X
     */
    default List<T> alphacut(List<T> X, double a) {
        return X.stream().filter(x -> contains(x) >= a).collect(Collectors.toList());
    }

    /**
     * Compute degree of fuzziness in discrete, finite universe of discourse X
     */
    default double degreeOfFuzziness(List<T> X) {
        return support(X).size() / (double) X.size();
    }

    /**
     * Return fuzzy set as a result of intersection of this object and another one
     */
    default FuzzySet<T> and(FuzzySet<T> anotherOne) {
        return x -> Math.min(this.contains(x), anotherOne.contains(x));
    }
}
