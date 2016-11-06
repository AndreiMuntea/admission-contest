package helpers.loader.serialisedLoader;

import domain.Candidate;
import java.util.Collection;

/**
 * Created by andrei on 11/2/2016.
 */
public class SerialisedCandidateLoader extends BaseSerialisedLoader<Candidate>{

    public SerialisedCandidateLoader(){}

    @Override
    public Collection<Candidate> load(String fileName) {
        return super.load(fileName);
    }

}
