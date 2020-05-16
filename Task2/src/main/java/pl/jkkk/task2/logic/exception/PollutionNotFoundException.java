package pl.jkkk.task2.logic.exception;

public class PollutionNotFoundException extends RuntimeException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public PollutionNotFoundException() {
    }

    public PollutionNotFoundException(String message) {
        super(message);
    }

    public PollutionNotFoundException(Throwable cause) {
        super(cause);
    }
}
    