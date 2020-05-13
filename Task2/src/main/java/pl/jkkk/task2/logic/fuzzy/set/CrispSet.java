package pl.jkkk.task2.logic.fuzzy.set;

import pl.jkkk.task2.logic.fuzzy.membershipfunction.MembershipFunction;

/**
 * This class represents crisp set, it is just concretization of
 * of a fuzzy set, some object can belongs to it in degree either 0 or 1.
 */
public class CrispSet extends FuzzySet {

    public CrispSet(MembershipFunction membershipFunction) {
        super(membershipFunction);
    }

    /**
     * The only difference to origin implementation is adding
     * some protection to return only 0 or 1 value.
     */
    @Override
    public double contains(double x) {
        double u = super.contains(x);
        if (u != 1.0 && u != 0.0) {
            throw new UnsupportedOperationException(
                    "Object can not belong to a crisp set to degree of " + u);
        }
        return u;
    }
}
