package pl.jkkk.task2.logic.fuzzy.set;

public class TrapezoidalFuzzySet extends FuzzySet {

    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public TrapezoidalFuzzySet(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double contains(double x) {
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
