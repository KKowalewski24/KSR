package pl.jkkk.task2.logic.fuzzy.set;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface DoubleValueExtractor<T> extends Function<T, Double>, Serializable {
}
