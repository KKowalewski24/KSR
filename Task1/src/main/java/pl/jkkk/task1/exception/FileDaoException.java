package pl.jkkk.task1.exception;

public class FileDaoException extends DaoException {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public FileDaoException() {
    }

    public FileDaoException(String message) {
        super(message);
    }

    public FileDaoException(Throwable cause) {
        super(cause);
    }
}
    