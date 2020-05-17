package pl.jkkk.task2.logic.fuzzy.set;

public class GaussianFuzzySet extends FuzzySet {

    private final double center;
    private final double width;

    public GaussianFuzzySet(double center, double width) {
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
    public double contains(double x) {
        return Math.exp(-(center - x) * (center - x) / (2 * width * width));
    }
}
