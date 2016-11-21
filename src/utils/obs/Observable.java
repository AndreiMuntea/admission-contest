package utils.obs;

/**
 * Created by andrei on 11/21/2016.
 */
public interface Observable<E> {
    void addObserver(Observer<E> o);
    void removeObserver(Observer<E> o);
    void notifyObservers();
}
