package pl.jkkk.task2.logic.fuzzy.set;

public class IntersectionFuzzySet<T> extends FuzzySet<T> {

    private final FuzzySet<T> A;
    private final FuzzySet<T> B;

    public IntersectionFuzzySet(FuzzySet<T> A, FuzzySet<T> B) {
        this.A = A;
        this.B = B;
    }

    public FuzzySet<T> getA() {
        return A;
    }

    public FuzzySet<T> getB() {
        return B;
    }

    @Override
    public double contains(T x) {
        return Math.min(A.contains(x), B.contains(x));
    }
}
