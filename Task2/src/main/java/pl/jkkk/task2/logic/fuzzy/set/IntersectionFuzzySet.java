package pl.jkkk.task2.logic.fuzzy.set;

public class IntersectionFuzzySet extends FuzzySet {

    private FuzzySet A;
    private FuzzySet B;

    public IntersectionFuzzySet(FuzzySet A, FuzzySet B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public double contains(double x) {
        return Math.min(A.contains(x), B.contains(x));
    }
}
