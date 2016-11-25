package utils;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import utils.obs.Observable;
import utils.obs.Observer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by andrei on 11/25/2016.
 */
public class CustomObservableList<E> extends SimpleListProperty implements Observable<E>{


    protected List<Observer<E>> observers;

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

    public CustomObservableList(){
        super(FXCollections.observableArrayList());
        observers = new ArrayList<>();
    }


    public void notifyObservers(Object object) {
        for(Observer<E> o : observers){
            o.update(this, object);
        }
    }

}
