package helpers.saver.textFileSaver;

import domain.Option;
import utils.Pair;

/**
 * Created by andrei on 12/1/2016.
 */
public class OptionFileSaver extends BaseFileSaver<Option> {

    public OptionFileSaver(){super();}

    public OptionFileSaver(String separator){super(separator);}

    @Override
    public String getFormat(Option object) {
        Pair<?, ?>  option = object.getID();
        return option.getFirst() + separator + option.getSecond();
    }
}
