package helpers.loader.textFileLoaders;

import domain.Candidate;
import helpers.exceptions.FileException;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by andrei on 11/5/2016.
 */
public class CandidateFileLoader extends BaseFileLoader<Candidate> {

    public CandidateFileLoader(){super();}

    public CandidateFileLoader(String separator){super(separator);}

    @Override
    public Collection<Candidate> load(String fileName) {
        return super.load(fileName);
    }

    @Override
    public Candidate createFromFormat(String part) throws FileException {
        String[] parts = part.split(Pattern.quote(separator));

        if (parts.length != 5) throw new FileException("Corrupted file!\n");

        try {
            return new Candidate(Integer.parseInt(parts[0]),parts[1],parts[2],parts[3],Double.parseDouble(parts[4]));
        } catch (NumberFormatException e) {
            throw new FileException("Corrupted file!\n");
        }
    }
}
