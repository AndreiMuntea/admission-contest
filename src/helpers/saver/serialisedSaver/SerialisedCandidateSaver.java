package helpers.saver.serialisedSaver;

import domain.Candidate;
import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public class SerialisedCandidateSaver extends BaseSerialisedSaver<Candidate> {

        public SerialisedCandidateSaver(){}

        @Override
        public void save(Collection<Candidate> collection, String fileName) {
            super.save(collection,fileName);
        }
}
