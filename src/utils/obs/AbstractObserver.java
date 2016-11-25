package utils.obs;

/**
 * Created by andrei on 11/21/2016.
 */
public abstract class AbstractObserver<E> implements Observer<E> {

    public AbstractObserver(){};

    @Override
    public abstract void update(Observable<E> observable, Object... objects);
}
