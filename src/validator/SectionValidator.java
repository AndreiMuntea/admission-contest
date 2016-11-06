package validator;

import domain.Section;
import validator.exceptions.ValidatorException;

/**
 * Created by andrei on 11/2/2016.
 */
public class SectionValidator implements IValidator<Section> {
    /**
     *
     * @param object - a Section. The Section to be validated.
     * @throws ValidatorException - if section is not valid.
     */
    @Override
    public void validate(Section object) throws ValidatorException {
        String errors = "";

        if (!validateID(object.getID()))
            errors += "ID can't be void!\n";
        if (!validateName(object.getName()))
            errors += "Name can't be void!\n";
        if (!validateAvailableSlots(object.getAvailableSlots()))
            errors += "There should be at least one available slot!\n";

        if (!errors.equals("")) {
            throw new ValidatorException(errors);
        }
    }

    private Boolean validateName(String name) {
        return !(name.equals(""));
    }

    private Boolean validateID(String ID) {
        return !(ID.equals(""));
    }

    private Boolean validateAvailableSlots(Integer availableSlots) {
        return (availableSlots > 0);
    }
}
