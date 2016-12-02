package utils;

/**
 * Created by andrei on 11/6/2016.
 */
public class Pair<E1,E2> {
    private E1 first;
    private E2 second;

    public Pair(E1 first, E2 second){
        this.first = first;
        this.second = second;
    }


    public E1 getFirst() {
        return first;
    }

    public void setFirst(E1 first) {
        this.first = first;
    }

    public E2 getSecond() {
        return second;
    }

    public void setSecond(E2 second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!first.equals(pair.first)) return false;
        return second.equals(pair.second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
