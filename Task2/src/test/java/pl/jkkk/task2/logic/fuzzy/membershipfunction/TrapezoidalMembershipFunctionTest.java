package pl.jkkk.task2.logic.fuzzy.membershipfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrapezoidalMembershipFunctionTest {

    @Test
    public void value() {
        TrapezoidalMembershipFunction function = 
            new TrapezoidalMembershipFunction(0.0, 1.0, 2.0, 3.0);
        Assertions.assertEquals(0.00, function.value(-0.5), 0.0000001);
        Assertions.assertEquals(0.00, function.value(0.00), 0.0000001);
        Assertions.assertEquals(0.25, function.value(0.25), 0.0000001);
        Assertions.assertEquals(0.50, function.value(0.50), 0.0000001);
        Assertions.assertEquals(0.75, function.value(0.75), 0.0000001);
        Assertions.assertEquals(1.00, function.value(1.00), 0.0000001);
        Assertions.assertEquals(1.00, function.value(1.25), 0.0000001);
        Assertions.assertEquals(1.00, function.value(1.50), 0.0000001);
        Assertions.assertEquals(1.00, function.value(1.75), 0.0000001);
        Assertions.assertEquals(1.00, function.value(2.00), 0.0000001);
        Assertions.assertEquals(0.75, function.value(2.25), 0.0000001);
        Assertions.assertEquals(0.50, function.value(2.50), 0.0000001);
        Assertions.assertEquals(0.25, function.value(2.75), 0.0000001);
        Assertions.assertEquals(0.00, function.value(3.00), 0.0000001);
        Assertions.assertEquals(0.00, function.value(3.50), 0.0000001);
    }
}
