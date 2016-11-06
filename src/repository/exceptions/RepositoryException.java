package repository.exceptions;

import utils.MyException;

/**
 * Created by andrei on 11/2/2016.
 */
public class RepositoryException extends MyException {
    public RepositoryException() {
        super("Repository exception!\n");
    }

    public RepositoryException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }

}
