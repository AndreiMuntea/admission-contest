package tests.validator;

import domain.Section;
import org.junit.Test;
import validator.IValidator;
import validator.SectionValidator;
import validator.exceptions.ValidatorException;

import static org.junit.Assert.*;

/**
 * Created by andrei on 11/3/2016.
 */
public class SectionValidatorTest {
    @Test
    public void validate() throws Exception {
        Section notValidSection = new Section();
        Section validSection = new Section("ID","name",40);
        IValidator<Section> validator = new SectionValidator();

        try{
            validator.validate(notValidSection);
            assertTrue(false);
        }catch(ValidatorException e){
            assertTrue(true);
        }

        try{
            validator.validate(validSection);
            assertTrue(true);
        }catch(ValidatorException e){
            assertTrue(false);
        }

    }

}