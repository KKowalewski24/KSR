package pl.jkkk.task2.logic.fuzzy.set;

public class TrapezoidalFuzzySet<T> implements FuzzySet<T> {

    private final DoubleValueExtractor<T> doubleValueExtractor;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double universeBegin;
    private final double universeEnd;

    public TrapezoidalFuzzySet(DoubleValueExtractor<T> doubleValueExtractor, double a, double b, double c, double d,
            double universeBegin, double universeEnd) {

        /* This trapez can be wrapped (from the end to the beginning) in any place,
        so firstly we have to normalize it basing on universe range */
        final double universeRange = universeEnd - universeBegin;
        if (b < a) {
            b += universeRange;
        }
        if (c < b) {
            c += universeRange;
        }
        if (d < c) {
            d += universeRange;
        }

        /* Now we can initialize fields */
        this.doubleValueExtractor = doubleValueExtractor;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.universeBegin = universeBegin;
        this.universeEnd = universeEnd;
    }

    public TrapezoidalFuzzySet(DoubleValueExtractor<T> doubleValueExtractor, double a, double b, double c, double d) {
        this(doubleValueExtractor, a, b, c, d, 0.0, 0.0);
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

    public double getUniverseBegin() {
        return universeBegin;
    }

    public double getUniverseEnd() {
        return universeEnd;
    }

    @Override
    public double contains(T object) {
        /* Get value from object's attribute and normalize it if necessary */
        double x = doubleValueExtractor.apply(object);
        if (x < a) {
            x += universeEnd - universeBegin;
        }

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
