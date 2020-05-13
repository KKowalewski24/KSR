package pl.jkkk.task2.logic.fuzzy.membershipfunction;

public class TrapezoidalMembershipFunction implements MembershipFunction {

    public final double a;
    public final double b;
    public final double c;
    public final double d;

    public TrapezoidalMembershipFunction(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double value(double x) {
        if (x > a && x < b) {
            return (x - a) / (b - a);
        } else if (x >= b && x <= c) {
            return 1.0;
        } else if (x > c && x < d) {
            return 1 - (x - c) / (d - c);
        } else {
            return 0.0;
        }
    }
}
