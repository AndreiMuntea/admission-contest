package helpers.loader;

import java.util.Collection;

/**
 * Created by andrei on 11/2/2016.
 */
public interface ILoader<E> {
    Collection<E> load(String fileName);
}
