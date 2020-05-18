package pl.jkkk.task2.logic.fuzzy.set;

public class ModifiedFuzzySet<T> extends FuzzySet<T> {

    private final FuzzySet<T> original;
    private final double R;

    public ModifiedFuzzySet(FuzzySet<T> original, double R) {
        this.original = original;
        this.R = R;
    }

    public FuzzySet<T> getOriginal() {
        return original;
    }

    public double getR() {
        return R;
    }

    @Override
    public double contains(T x) {
        return Math.pow(original.contains(x), R);
    }
}
