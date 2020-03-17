package pl.jkkk.task1.exception;

public class NoDocumentsException extends RuntimeException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public NoDocumentsException() {
    }

    public NoDocumentsException(String message) {
        super(message);
    }

    public NoDocumentsException(Throwable cause) {
        super(cause);
    }
}
    