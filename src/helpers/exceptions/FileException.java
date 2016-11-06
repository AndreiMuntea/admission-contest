package helpers.exceptions;

import utils.MyException;

/**
 * Created by andrei on 11/2/2016.
 */
public class FileException extends MyException {
    public FileException() {
        super("File exception!\n");
    }

    public FileException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }
}
