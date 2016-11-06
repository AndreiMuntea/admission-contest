package tests.validator;

import domain.Candidate;
import org.junit.Test;
import validator.CandidateValidator;
import validator.IValidator;
import validator.exceptions.ValidatorException;

import static org.junit.Assert.*;

/**
 * Created by andrei on 11/3/2016.
 */
public class CandidateValidatorTest {
    @Test
    public void validate() throws Exception {
        Candidate notValidCandidate = new Candidate();
        Candidate validCandidate = new Candidate(1,"name","address","0727172644", 4.2);
        IValidator<Candidate> validator = new CandidateValidator();

        try{
            validator.validate(notValidCandidate);
            assertTrue(false);
        }catch(ValidatorException e){
            assertTrue(true);
        }

        try{
            validator.validate(validCandidate);
            assertTrue(true);
        }catch(ValidatorException e){
            assertTrue(false);
        }

    }

}