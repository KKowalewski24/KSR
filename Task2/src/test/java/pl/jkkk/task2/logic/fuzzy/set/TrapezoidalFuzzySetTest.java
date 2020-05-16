package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrapezoidalFuzzySetTest {

    @Test
    public void contains() {
        TrapezoidalFuzzySet set = 
            new TrapezoidalFuzzySet(0.0, 1.0, 2.0, 3.0);
        Assertions.assertEquals(0.00, set.contains(-0.5), 0.0000001);
        Assertions.assertEquals(0.00, set.contains(0.00), 0.0000001);
        Assertions.assertEquals(0.25, set.contains(0.25), 0.0000001);
        Assertions.assertEquals(0.50, set.contains(0.50), 0.0000001);
        Assertions.assertEquals(0.75, set.contains(0.75), 0.0000001);
        Assertions.assertEquals(1.00, set.contains(1.00), 0.0000001);
        Assertions.assertEquals(1.00, set.contains(1.25), 0.0000001);
        Assertions.assertEquals(1.00, set.contains(1.50), 0.0000001);
        Assertions.assertEquals(1.00, set.contains(1.75), 0.0000001);
        Assertions.assertEquals(1.00, set.contains(2.00), 0.0000001);
        Assertions.assertEquals(0.75, set.contains(2.25), 0.0000001);
        Assertions.assertEquals(0.50, set.contains(2.50), 0.0000001);
        Assertions.assertEquals(0.25, set.contains(2.75), 0.0000001);
        Assertions.assertEquals(0.00, set.contains(3.00), 0.0000001);
        Assertions.assertEquals(0.00, set.contains(3.50), 0.0000001);
    }
}
