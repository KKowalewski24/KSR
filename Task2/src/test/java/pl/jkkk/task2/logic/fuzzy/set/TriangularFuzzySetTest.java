package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangularFuzzySetTest {

    @Test
    public void contains() {
        TriangularFuzzySet<Double> set = new TriangularFuzzySet<>(x -> x, 2.0, 3.0, 5.0);
        Assertions.assertEquals(0.0, set.contains(1.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(2.0), 0.0000001);
        Assertions.assertEquals(0.5, set.contains(2.5), 0.0000001);
        Assertions.assertEquals(1.0, set.contains(3.0), 0.0000001);
        Assertions.assertEquals(0.5, set.contains(4.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(5.0), 0.0000001);
        Assertions.assertEquals(0.0, set.contains(6.0), 0.0000001);
    }
}
