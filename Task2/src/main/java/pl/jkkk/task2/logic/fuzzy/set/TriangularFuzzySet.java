package pl.jkkk.task2.logic.fuzzy.set;

public class TriangularFuzzySet<T> extends TrapezoidalFuzzySet<T> {

    public TriangularFuzzySet(DoubleValueExtractor<T> doubleValueExtractor,
                              double a, double b, double c,
                              double universeBegin, double universeEnd) {
        super(doubleValueExtractor, a, b, b, c, universeBegin, universeEnd);
    }

    public TriangularFuzzySet(DoubleValueExtractor<T> doubleValueExtractor,
                              double a, double b, double c) {
        this(doubleValueExtractor, a, b, c, 0.0, 0.0);
    }
}
