package helpers.loader.textFileLoaders;

import helpers.exceptions.FileException;

import java.util.Collection;
import java.util.regex.Pattern;

import domain.Section;

/**
 * Created by andrei on 11/2/2016.
 */
public class SectionFileLoader extends BaseFileLoader<Section>{



    public SectionFileLoader() {
        super();
    }

    public SectionFileLoader(String separator) {
        super(separator);
    }

    @Override
    public Collection<Section> load(String fileName){
        return super.load(fileName);
    }

    @Override
    public Section createFromFormat(String part) throws FileException {
        String[] parts = part.split(Pattern.quote(separator));

        if (parts.length != 3) throw new FileException("Corrupted file!\n");

        try {
            return new Section(parts[0], parts[1], Integer.parseInt(parts[2]));
        } catch (NumberFormatException e) {
            throw new FileException("Corrupted file!\n");
        }
    }
}
