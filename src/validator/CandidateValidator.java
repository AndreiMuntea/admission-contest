package validator;

import domain.Candidate;
import validator.exceptions.ValidatorException;

/**
 * Created by andrei on 11/2/2016.
 */
public class CandidateValidator implements IValidator<Candidate> {

    /**
     *
     * @param object - a Candidate. The Candidate to be validated.
     * @throws ValidatorException - if Candidate is not valid.
     */
    @Override
    public void validate(Candidate object) throws ValidatorException {
        String errors = "";

        if (!validateID(object.getID()))
            errors += "ID must be greater than 0!\n";
        if (!validateName(object.getName()))
            errors += "Name can't be void!\n";
        if (!validatePhoneNumber(object.getPhoneNumber()))
            errors += "Phone number must start with 0 and be a 10 digit string!\n";
        if (!validateAddress(object.getAddress()))
            errors += "Address can't be void!\n";
        if (!validateGrade(object.getGrade()))
            errors += "Grade should be a number in range 1 - 10!\n";

        if (!errors.equals("")) {
            throw new ValidatorException(errors);
        }
    }

    private Boolean validateID(Integer ID) {
        return (ID > 0);
    }

    private Boolean validateName(String name) {
        return (!name.equals(""));
    }

    private Boolean validateAddress(String address) {
        return (!address.equals(""));
    }

    private Boolean validateGrade(Double grade) {
        return (grade >= 1 && grade <= 10);
    }

    private Boolean validatePhoneNumber(String phoneNumber) {
        return (phoneNumber.length() == 10 && (phoneNumber.matches("0[0-9]+")));
    }

}
