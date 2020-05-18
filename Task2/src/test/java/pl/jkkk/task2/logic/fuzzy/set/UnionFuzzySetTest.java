package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnionFuzzySetTest {

    @Test
    public void contains() {
        TriangularFuzzySet<Double> A = new TriangularFuzzySet<>(x -> x, 0, 1, 2);
        TriangularFuzzySet<Double> B = new TriangularFuzzySet<>(x -> x, 1, 2, 3);
        UnionFuzzySet<Double> C = new UnionFuzzySet<>(A, B);
        Assertions.assertEquals(0.0, C.contains(-1.0), 0.0000001);
        Assertions.assertEquals(0.0, C.contains(0.0), 0.0000001);
        Assertions.assertEquals(0.5, C.contains(0.5), 0.0000001);
        Assertions.assertEquals(1.0, C.contains(1.0), 0.0000001);
        Assertions.assertEquals(0.5, C.contains(1.5), 0.0000001);
        Assertions.assertEquals(1.0, C.contains(2.0), 0.0000001);
        Assertions.assertEquals(0.5, C.contains(2.5), 0.0000001);
        Assertions.assertEquals(0.0, C.contains(3.0), 0.0000001);
        Assertions.assertEquals(0.0, C.contains(4.0), 0.0000001);
    }
}
