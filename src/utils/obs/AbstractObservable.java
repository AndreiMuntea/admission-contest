package utils.obs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 11/21/2016.
 */
public class AbstractObservable<E> implements Observable<E> {

    private List<Observer<E>> observers;

    public AbstractObservable() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer<E> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<E> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer<E> o : observers){
            o.update(this);
        }
    }
}
