package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModifiedFuzzySetTest {

    @Test
    public void contains() {
        TriangularFuzzySet<Double> A = new TriangularFuzzySet<>(x -> x, 0, 1, 2);
        ModifiedFuzzySet<Double> B = new ModifiedFuzzySet<>(A, 2);
        Assertions.assertEquals(0.0, B.contains(0.0), 0.0000001);
        Assertions.assertEquals(0.16, B.contains(0.4), 0.0000001);
        Assertions.assertEquals(0.25, B.contains(0.5), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(1.0), 0.0000001);
    }
}
