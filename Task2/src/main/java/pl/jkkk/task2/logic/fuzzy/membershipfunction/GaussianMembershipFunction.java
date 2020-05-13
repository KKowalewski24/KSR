package pl.jkkk.task2.logic.fuzzy.membershipfunction;

public class GaussianMembershipFunction implements MembershipFunction {
    
    private final double center;
    private final double width;

    public GaussianMembershipFunction(double center, double width) {
        this.center = center;
        this.width = width;
    }

    @Override
    public double value(double x) {
        return Math.exp(- (center - x) * (center - x) / (2 * width * width));
    }
}
