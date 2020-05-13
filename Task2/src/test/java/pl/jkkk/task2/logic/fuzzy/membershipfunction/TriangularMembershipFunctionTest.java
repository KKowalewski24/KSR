package pl.jkkk.task2.logic.fuzzy.membershipfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangularMembershipFunctionTest {

    @Test
    public void value() {
        TriangularMembershipFunction function =
            new TriangularMembershipFunction(2.0, 3.0, 5.0);
        Assertions.assertEquals(0.0, function.value(1.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(2.0), 0.0000001);
        Assertions.assertEquals(0.5, function.value(2.5), 0.0000001);
        Assertions.assertEquals(1.0, function.value(3.0), 0.0000001);
        Assertions.assertEquals(0.5, function.value(4.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(5.0), 0.0000001);
        Assertions.assertEquals(0.0, function.value(6.0), 0.0000001);
    }
}
