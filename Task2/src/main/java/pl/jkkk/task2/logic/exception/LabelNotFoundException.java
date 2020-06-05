package pl.jkkk.task2.logic.exception;

public class LabelNotFoundException extends RuntimeException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public LabelNotFoundException() {
    }

    public LabelNotFoundException(String message) {
        super(message);
    }

    public LabelNotFoundException(Throwable cause) {
        super(cause);
    }
}
    