package helpers.loader.textFileLoaders;

import domain.Option;
import helpers.exceptions.FileException;
import utils.Pair;

import java.util.regex.Pattern;

/**
 * Created by andrei on 12/1/2016.
 */
public class OptionFileLoader extends BaseFileLoader<Option> {

    public OptionFileLoader(String separator) {
        super(separator);
    }

    public OptionFileLoader() {
        super();
    }

    @Override
    public Option createFromFormat(String part) throws FileException {
        String[] parts = part.split(Pattern.quote(separator));
        if (parts.length != 2) throw new FileException("Corrupted file!\n");

        try {
            Pair<Integer, String> option = new Pair<>(Integer.parseInt(parts[0]), parts[1]);
            return new Option(option);
        } catch (NumberFormatException e) {
            throw new FileException("Corrupted file!\n");
        }
    }
}
