package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GaussianFuzzySetTest {
    
    @Test
    public void contains() {
        GaussianFuzzySet set = new GaussianFuzzySet(0.0, 0.5);
        Assertions.assertEquals(1.0, set.contains(0.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(-10.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(10.0), 0.0000001);

        set = new GaussianFuzzySet(1.0, 0.5);
        Assertions.assertEquals(1.0, set.contains(1.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(-10.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(10.0), 0.0000001);
    }
}
