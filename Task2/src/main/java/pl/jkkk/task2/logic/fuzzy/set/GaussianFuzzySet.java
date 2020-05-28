package pl.jkkk.task2.logic.fuzzy.set;

public class GaussianFuzzySet<T> implements FuzzySet<T> {

    private final DoubleValueExtractor<T> doubleValueExtractor;
    private final double center;
    private final double width;

    public GaussianFuzzySet(DoubleValueExtractor<T> doubleValueExtractor, double center, double width) {
        this.doubleValueExtractor = doubleValueExtractor;
        this.center = center;
        this.width = width;
    }

    public double getCenter() {
        return center;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double contains(T x) {
        double value = doubleValueExtractor.apply(x);
        return Math.exp(-(center - value) * (center - value) / (2 * width * width));
    }
}
