package repository.exceptions;

/**
 * Created by andrei on 11/2/2016.
 */
public class CorruptedFileException extends RepositoryException {
    public CorruptedFileException() {
        super("Corrupted file exception!\n");
    }

    public CorruptedFileException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }
}
