package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComplementFuzzySetTest {

    @Test
    public void contains() {
        TriangularFuzzySet<Double> A = new TriangularFuzzySet<>(x -> x, 0, 1, 2);
        ComplementFuzzySet<Double> B = new ComplementFuzzySet<>(A);
        Assertions.assertEquals(1.0, B.contains(-1.0), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(0.0), 0.0000001);
        Assertions.assertEquals(0.5, B.contains(0.5), 0.0000001);
        Assertions.assertEquals(0.0, B.contains(1.0), 0.0000001);
        Assertions.assertEquals(0.5, B.contains(1.5), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(2.0), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(3.0), 0.0000001);
    }
}
