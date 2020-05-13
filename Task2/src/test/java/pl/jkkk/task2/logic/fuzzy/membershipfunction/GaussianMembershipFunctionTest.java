package pl.jkkk.task2.logic.fuzzy.membershipfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GaussianMembershipFunctionTest {

    @Test
    public void value() {
        GaussianMembershipFunction function = 
            new GaussianMembershipFunction(0.0, 0.5);
        Assertions.assertEquals(1.0, function.value(0.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(-10.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(10.0), 0.0000001);

        function = new GaussianMembershipFunction(1.0, 0.5);
        Assertions.assertEquals(1.0, function.value(1.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(-10.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(10.0), 0.0000001);
    }
}
