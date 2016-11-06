package domain;

import java.io.Serializable;

/**
 * Created by andrei on 11/2/2016.
 */
public interface HasID<T> extends Serializable {
    /**
     *
     * @return a T - the ID.
     */
    T getID();
}
