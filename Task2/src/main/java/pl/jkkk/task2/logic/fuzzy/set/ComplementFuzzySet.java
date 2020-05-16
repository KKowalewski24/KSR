package pl.jkkk.task2.logic.fuzzy.set;

public class ComplementFuzzySet implements FuzzySet {

    private FuzzySet original;

    public ComplementFuzzySet(FuzzySet original) {
        this.original = original;
    }

    @Override
    public double contains(double x) {
        return 1.0 - original.contains(x);
    }
}
