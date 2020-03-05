package pl.jkkk.task1.featureextraction;

public enum Metric {
    EUCLIDEAN("Euclidean"),
    MANHATTAN("Manhattan"),
    CHEBYSHEV("Chebyshev");

    public final String label;

    Metric(String label) {
        this.label = label;
    }
}
