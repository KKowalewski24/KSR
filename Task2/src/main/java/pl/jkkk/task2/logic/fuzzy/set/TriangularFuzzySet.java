package pl.jkkk.task2.logic.fuzzy.set;

public class TriangularFuzzySet<T> extends TrapezoidalFuzzySet<T> {

    public TriangularFuzzySet(DoubleValueExtractor<T> doubleValueExtractor, double a, double b, double c) {
        super(doubleValueExtractor, a, b, b, c);
    }
}
