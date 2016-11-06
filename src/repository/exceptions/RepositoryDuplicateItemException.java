package repository.exceptions;

/**
 * Created by andrei on 11/2/2016.
 */
public class RepositoryDuplicateItemException extends RepositoryException {
    public RepositoryDuplicateItemException() {
        super("Repository duplicate item exception!\n");
    }

    public RepositoryDuplicateItemException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }
}
