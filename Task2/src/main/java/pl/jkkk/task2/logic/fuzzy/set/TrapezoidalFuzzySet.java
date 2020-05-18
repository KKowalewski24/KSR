package pl.jkkk.task2.logic.fuzzy.set;

public class TrapezoidalFuzzySet<T> extends FuzzySet<T> {

    private final DoubleValueExtractor<T> doubleValueExtractor;
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public TrapezoidalFuzzySet(DoubleValueExtractor<T> doubleValueExtractor, double a, double b, double c, double d) {
        this.doubleValueExtractor = doubleValueExtractor;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    @Override
    public double contains(T object) {
        double x = doubleValueExtractor.apply(object);
        if (x > a && x < b) {
            return (x - a) / (b - a);
        } else if (x >= b && x <= c) {
            return 1.0;
        } else if (x > c && x < d) {
            return 1 - (x - c) / (d - c);
        } else {
            return 0.0;
        }
    }
}
