package pl.jkkk.task1.featureextraction;

import pl.jkkk.task1.constant.Constants;
import pl.jkkk.task1.exception.MetricNotSupportedException;

import static pl.jkkk.task1.constant.Constants.CHEBYSHEV_ABBREVIATION;
import static pl.jkkk.task1.constant.Constants.EUCLIDEAN_ABBREVIATION;
import static pl.jkkk.task1.constant.Constants.MANHATTAN_ABBREVIATION;

public enum Metric {
    EUCLIDEAN(Constants.EUCLIDEAN),
    MANHATTAN(Constants.MANHATTAN),
    CHEBYSHEV(Constants.CHEBYSHEV);

    public final String label;

    Metric(String label) {
        this.label = label;
    }

    public static Metric convertAbbreviationToMetric(String metricAbbreviation)
            throws MetricNotSupportedException {

        switch (metricAbbreviation) {
            case EUCLIDEAN_ABBREVIATION: {
                return Metric.EUCLIDEAN;

            }
            case MANHATTAN_ABBREVIATION: {
                return Metric.MANHATTAN;

            }
            case CHEBYSHEV_ABBREVIATION: {
                return Metric.CHEBYSHEV;
            }
            default: {
                throw new MetricNotSupportedException();
            }
        }
    }
}
