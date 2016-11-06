package helpers.saver.textFileSaver;

import domain.Section;
import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public class SectionFileSaver extends BaseFileSaver<Section> {

    public SectionFileSaver() {
        super();
    }

    public SectionFileSaver(String separator) {
        super(separator);
    }

    @Override
    public void save(Collection<Section> collection, String fileName) {
        super.save(collection, fileName);
    }

    @Override
    public String getFormat(Section object) {
        return object.getID() + separator +
                object.getName() + separator +
                object.getAvailableSlots();
    }


}
