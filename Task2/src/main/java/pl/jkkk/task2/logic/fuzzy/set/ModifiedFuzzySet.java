package pl.jkkk.task2.logic.fuzzy.set;

public class ModifiedFuzzySet extends FuzzySet {

    private final FuzzySet original;
    private final double R;

    public ModifiedFuzzySet(FuzzySet original, double R) {
        this.original = original;
        this.R = R;
    }

    @Override
    public double contains(double x) {
        return Math.pow(original.contains(x), R);
    }
}
