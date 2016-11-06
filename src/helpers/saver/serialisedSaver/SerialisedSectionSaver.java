package helpers.saver.serialisedSaver;

import domain.Section;
import java.util.Collection;


/**
 * Created by andrei on 11/5/2016.
 */
public class SerialisedSectionSaver extends BaseSerialisedSaver<Section> {
    public SerialisedSectionSaver(){}

    @Override
    public void save(Collection<Section> collection, String fileName) {
        super.save(collection,fileName);
    }
}
