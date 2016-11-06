package helpers.saver;

import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public abstract class AbstractSaver<Element> implements  ISaver<Element> {
    @Override
    public abstract void save(Collection<Element> collection, String fileName);
}
