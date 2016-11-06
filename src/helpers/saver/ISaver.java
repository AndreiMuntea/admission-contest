package helpers.saver;

import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public interface ISaver<Element> {
    void save(Collection<Element> collection, String fileName);
}
