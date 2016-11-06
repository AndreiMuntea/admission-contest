package repository.exceptions;

/**
 * Created by andrei on 11/2/2016.
 */
public class RepositoryNotFoundException extends  RepositoryException {

    public RepositoryNotFoundException() {
        super("Repository not found exception!\n");
    }

    public RepositoryNotFoundException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }
}
