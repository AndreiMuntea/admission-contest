package domain;

import utils.Pair;

/**
 * Created by andrei on 11/6/2016.
 */
public class Option<E1, E2> implements HasID<Pair<E1, E2>> {

    private Pair<E1, E2> ID;

    public Option(Pair<E1, E2> ID){
        this.ID = ID;
    }

    @Override
    public Pair<E1, E2> getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option<?, ?> option = (Option<?, ?>) o;

        return ID != null ? ID.equals(option.ID) : option.ID == null;

    }

    @Override
    public String toString() {
        return "Option{" + ID +'}';
    }
}
