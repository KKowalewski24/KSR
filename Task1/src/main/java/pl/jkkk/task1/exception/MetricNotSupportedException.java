package pl.jkkk.task1.exception;

public class MetricNotSupportedException extends Exception {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public MetricNotSupportedException() {
    }

    public MetricNotSupportedException(String message) {
        super(message);
    }

    public MetricNotSupportedException(Throwable cause) {
        super(cause);
    }
}
    