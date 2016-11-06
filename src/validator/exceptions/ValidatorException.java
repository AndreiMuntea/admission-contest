package validator.exceptions;

import utils.MyException;

/**
 * Created by andrei on 11/2/2016.
 */
public class ValidatorException extends MyException {

    public ValidatorException() {
        super("Validator exception!\n");
    }

    public ValidatorException(String msg) {
        super(msg);
    }

    public String getError() {
        return super.getError();
    }
}
