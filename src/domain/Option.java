package domain;

import utils.Pair;

/**
 * Created by andrei on 11/6/2016.
 */
public class Option implements HasID<Pair<Integer, String>> {

    private Pair<Integer, String> ID;

    public Option(Pair<Integer, String> ID) {
        this.ID = ID;
    }

    @Override
    public Pair<Integer, String> getID() {
        return ID;
    }

    public Integer getCandidateID() {
        return ID.getFirst();
    }

    public String getSectionID() {
        return ID.getSecond();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option option = (Option) o;

        return ID != null ? ID.equals(option.ID) : option.ID == null;

    }

    @Override
    public String toString() {
        return "Option{" + ID + '}';
    }
}
