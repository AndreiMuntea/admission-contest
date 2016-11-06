package repository.exceptions;

/**
 * Created by andrei on 11/2/2016.
 */
public class RepositoryChangedIDException extends RepositoryException {
    public RepositoryChangedIDException() {
        super("Repository ID can't be changed exception!\n");
    }

    public RepositoryChangedIDException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }
}
