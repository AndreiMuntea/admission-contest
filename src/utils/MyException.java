package utils;

/**
 * Created by andrei on 11/2/2016.
 */
public class MyException extends Exception {

    public MyException() {
        super("Generic Exception!\n");
    }

    public MyException(String msg) {
        super(msg);
    }

    public String getError()
    {
        return super.getMessage();
    }
}
