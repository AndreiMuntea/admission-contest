package tests.repository;

import validator.IValidator;
import validator.exceptions.ValidatorException;

/**
 * Created by andrei on 11/14/2016.
 */
public class MockValidator implements IValidator<MockEntity> {
    @Override
    public void validate(MockEntity object) throws ValidatorException {

    }
}
