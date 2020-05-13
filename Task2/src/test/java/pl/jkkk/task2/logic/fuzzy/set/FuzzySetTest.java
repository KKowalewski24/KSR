package pl.jkkk.task2.logic.fuzzy.set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.jkkk.task2.logic.fuzzy.membershipfunction.TriangularMembershipFunction;

public class FuzzySetTest {
        
    FuzzySet A = new FuzzySet(new TriangularMembershipFunction(0.0, 1.0, 2.0));
    FuzzySet B = new FuzzySet(x -> {
        if (x == 0.5) {
            return 0.7;
        } else {
            return 0.0;
        }
    });

    @Test
    public void basicUsageContainsHeight() {
        assertEquals(0.25, A.contains(1.75), 0.0000001);
        assertEquals(0.25, A.contains(0.25), 0.0000001);
        assertEquals(0.70, B.contains(0.50), 0.0000001);
        assertEquals(0.00, B.contains(0.51), 0.0000001);
    }

    @Test
    public void complement() {
        FuzzySet C = A.complement();
        FuzzySet D = B.complement();
        assertEquals(0.75, C.contains(1.75), 0.0000001);
        assertEquals(0.75, C.contains(0.25), 0.0000001);
        assertEquals(0.30, D.contains(0.50), 0.0000001);
        assertEquals(1.00, D.contains(0.51), 0.0000001);
    }

    @Test
    public void intersection() {
        FuzzySet C = A.intersection(B);
        assertEquals(0.00, C.contains(0.25), 0.0000001);
        assertEquals(0.50, C.contains(0.50), 0.0000001);
        assertEquals(0.00, C.contains(0.75), 0.0000001);
    }

    @Test
    public void union() {
        FuzzySet C = A.union(B);
        assertEquals(0.25, C.contains(0.25), 0.0000001);
        assertEquals(0.70, C.contains(0.50), 0.0000001);
        assertEquals(0.75, C.contains(0.75), 0.0000001);
    }

    @Test
    public void support() {
        CrispSet C = A.support();
        assertEquals(0.0, C.contains(-1.0));
        assertEquals(0.0, C.contains(0.0));
        assertEquals(1.0, C.contains(0.1));
        assertEquals(1.0, C.contains(0.2));
        assertEquals(1.0, C.contains(1.0));
        assertEquals(1.0, C.contains(1.9));
        assertEquals(0.0, C.contains(2.0));
        assertEquals(0.0, C.contains(2.1));
    }
}
