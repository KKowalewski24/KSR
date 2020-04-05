package pl.jkkk.task1.exception;

public class NoOccurrencesException extends RuntimeException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public NoOccurrencesException() {
    }

    public NoOccurrencesException(String message) {
        super(message);
    }

    public NoOccurrencesException(Throwable cause) {
        super(cause);
    }
}
    