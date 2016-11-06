package helpers.saver.textFileSaver;

import domain.Candidate;

import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public class CandidateFileSaver extends BaseFileSaver<Candidate> {

    public CandidateFileSaver() {
        super();
    }

    public CandidateFileSaver(String separator) {
        super(separator);
    }

    @Override
    public void save(Collection<Candidate> collection, String fileName) {
        super.save(collection, fileName);
    }


    @Override
    public String getFormat(Candidate object) {
        return object.getID() + separator +
                object.getName() + separator +
                object.getAddress() + separator +
                object.getPhoneNumber() + separator +
                object.getGrade();
    }
}
