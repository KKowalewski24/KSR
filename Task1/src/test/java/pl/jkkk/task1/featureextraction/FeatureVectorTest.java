package pl.jkkk.task1.featureextraction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jkkk.task1.exception.MetricNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;

class FeatureVectorTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private FeatureVector first = new FeatureVector();
    private FeatureVector second = new FeatureVector();

    /*------------------------ METHODS REGION ------------------------*/
    @BeforeEach
    void setUp() {
        first.add(-7.0);
        first.add(-4.0);
        second.add(17.0);
        second.add(6.0);
    }

    @Test
    void calculateEuclideanMetricTest() throws MetricNotSupportedException {
        assertEquals(26, FeatureVector.calculateDistance(first, second, Metric.EUCLIDEAN));
    }

    @Test
    void calculateManhattanMetricTest() throws MetricNotSupportedException {
        assertEquals(34, FeatureVector.calculateDistance(first, second, Metric.MANHATTAN));
    }

    @Test
    void calculateChebyshevMetricTest() throws MetricNotSupportedException {
        assertEquals(24, FeatureVector.calculateDistance(first, second, Metric.CHEBYSHEV));
    }
}
