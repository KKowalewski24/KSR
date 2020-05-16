package pl.jkkk.task2.logic.exception;

public class LinguisticQuantifierNotFoundException extends RuntimeException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public LinguisticQuantifierNotFoundException() {
    }

    public LinguisticQuantifierNotFoundException(String message) {
        super(message);
    }

    public LinguisticQuantifierNotFoundException(Throwable cause) {
        super(cause);
    }
}
    