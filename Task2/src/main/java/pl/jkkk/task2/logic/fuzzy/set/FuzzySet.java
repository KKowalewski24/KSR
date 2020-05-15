package pl.jkkk.task2.logic.fuzzy.set;

import pl.jkkk.task2.logic.fuzzy.membershipfunction.MembershipFunction;

/**
 * This class represents fuzzy set, it contains a characteristic function
 * and provide basic operations implementation.
 */
public class FuzzySet {
    
    private final MembershipFunction membershipFunction;

    public FuzzySet(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    /**
     * This method returns degree of membership of an object to the fuzzy set
     * basing directly on membership function result.
     */
    public double contains(double x) {
        return membershipFunction.value(x);
    }

    public FuzzySet complement() {
        return new FuzzySet((x) -> {
            return 1 - membershipFunction.value(x);
        });
    }

    public FuzzySet intersection(FuzzySet B) {
        return new FuzzySet((x) -> {
            return Math.min(B.membershipFunction.value(x), membershipFunction.value(x));
        });
    }

    public FuzzySet union(FuzzySet B) {
        return new FuzzySet((x) -> {
            return Math.max(B.membershipFunction.value(x), membershipFunction.value(x));
        });
    }

    public CrispSet support() {
        return new CrispSet((x) -> {
            if (membershipFunction.value(x) > 0.0) {
                return 1.0;
            } else {
                return 0.0;
            }
        });
    }
}
