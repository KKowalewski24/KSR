package pl.jkkk.task2.logic.fuzzy.set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ComplementFuzzySetTest {
    
    @Test
    public void contains() {
        TriangularFuzzySet A = new TriangularFuzzySet(0, 1, 2);
        ComplementFuzzySet B = new ComplementFuzzySet(A);
        Assertions.assertEquals(1.0, B.contains(-1.0), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(0.0), 0.0000001);
        Assertions.assertEquals(0.5, B.contains(0.5), 0.0000001);
        Assertions.assertEquals(0.0, B.contains(1.0), 0.0000001);
        Assertions.assertEquals(0.5, B.contains(1.5), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(2.0), 0.0000001);
        Assertions.assertEquals(1.0, B.contains(3.0), 0.0000001);
    }
}
