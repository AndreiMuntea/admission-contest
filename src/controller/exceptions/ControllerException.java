package controller.exceptions;

import utils.MyException;

/**
 * Created by andrei on 11/6/2016.
 */
public class ControllerException extends MyException {
    public ControllerException() {
        super("Controller Exception!\n");
    }

    public ControllerException(String msg) {
        super(msg);
    }

    @Override
    public String getError() {
        return super.getError();
    }
}
