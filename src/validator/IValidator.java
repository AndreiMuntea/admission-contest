package validator;

import validator.exceptions.ValidatorException;

/**
 * Created by andrei on 11/2/2016.
 */
public interface IValidator<T> {
    /**
     * @brief Validate an entity.
     *
     * @param object - a T. The object to be validated.
     * @throws ValidatorException if the object is not valid.
     */
    void validate(T object) throws ValidatorException;
}
