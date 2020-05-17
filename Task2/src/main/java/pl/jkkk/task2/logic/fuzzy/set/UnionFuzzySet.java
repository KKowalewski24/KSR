package pl.jkkk.task2.logic.fuzzy.set;

public class UnionFuzzySet extends FuzzySet {

    private FuzzySet A;
    private FuzzySet B;

    public UnionFuzzySet(FuzzySet A, FuzzySet B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public double contains(double x) {
        return Math.max(A.contains(x), B.contains(x));
    }
}
