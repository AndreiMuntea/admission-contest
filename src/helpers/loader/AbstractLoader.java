package helpers.loader;

import java.util.Collection;

/**
 * Created by andrei on 11/2/2016.
 */
public abstract class AbstractLoader<E> implements ILoader<E> {

    public AbstractLoader() {}

    @Override
    public abstract Collection<E> load(String fileName);
}
