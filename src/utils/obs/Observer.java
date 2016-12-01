package utils.obs;

/**
 * Created by andrei on 11/21/2016.
 */
public interface Observer<E> {
    void update (Observable<E> observable);
    void update (Observable<E> observable, Object o);
}
