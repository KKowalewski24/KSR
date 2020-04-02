//package pl.jkkk.task1.featureextraction;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class FeatureVectorTest {
//
//    /*------------------------ FIELDS REGION ------------------------*/
//    private FeatureVector first = new FeatureVector();
//    private FeatureVector second = new FeatureVector();
//
//    /*------------------------ METHODS REGION ------------------------*/
//    @BeforeEach
//    void setUp() {
//        first.add(-7.0);
//        first.add(-4.0);
//        second.add(17.0);
//        second.add(6.0);
//    }
//
//    @Test
//    void calculateEuclideanMetricTest() {
//        assertEquals(26, FeatureVector.calculateDistance(first, second, Metric.EUCLIDEAN));
//    }
//
//    @Test
//    void calculateManhattanMetricTest() {
//        assertEquals(34, FeatureVector.calculateDistance(first, second, Metric.MANHATTAN));
//    }
//
//    @Test
//    void calculateChebyshevMetricTest() {
//        assertEquals(24, FeatureVector.calculateDistance(first, second, Metric.CHEBYSHEV));
//    }
//}
