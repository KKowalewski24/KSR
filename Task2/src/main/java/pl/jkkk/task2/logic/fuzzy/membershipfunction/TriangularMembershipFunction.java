package pl.jkkk.task2.logic.fuzzy.membershipfunction;

public class TriangularMembershipFunction extends TrapezoidalMembershipFunction {
    public TriangularMembershipFunction(double a, double b, double c) {
        super(a, b, b, c);
    }
}
