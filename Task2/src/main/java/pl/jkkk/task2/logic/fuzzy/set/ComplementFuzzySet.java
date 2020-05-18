package pl.jkkk.task2.logic.fuzzy.set;

public class ComplementFuzzySet<T> extends FuzzySet<T> {

    private final FuzzySet<T> original;

    public ComplementFuzzySet(FuzzySet<T> original) {
        this.original = original;
    }

    public FuzzySet<T> getOriginal() {
        return original;
    }

    @Override
    public double contains(T x) {
        return 1.0 - original.contains(x);
    }
}
