package services;

import controller.CandidateController;
import domain.Candidate;
import utils.MyException;
import utils.obs.AbstractObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 12/1/2016.
 */
public class CandidateServices extends AbstractObservable {
    private CandidateController candidateController;


    public CandidateServices(CandidateController candidateController) {
        this.candidateController = candidateController;
    }

    public void addCandidate(String candidateID, String candidateName, String candidateAddress, String candidatePhoneNumber, String candidateGrade) throws MyException {
        candidateController.addElement(candidateID, candidateName, candidateAddress, candidatePhoneNumber, candidateGrade);
        notifyObservers();
    }

    public void removeCandidate(String candidateID) throws MyException {
        candidateController.removeElement(candidateID);
        notifyObservers();
        notifyObservers(candidateID);
    }

    public void updateCandidate(String candidateID, String candidateName, String candidateAddress, String candidatePhoneNumber, String candidateGrade) throws MyException {
        candidateController.updateElement(candidateID, candidateName, candidateAddress, candidatePhoneNumber, candidateGrade);
        notifyObservers();
    }

    public List<Candidate> getAll() {
        return new ArrayList<>(candidateController.getAll());
    }

    public List<Candidate> filterByPrefix(String prefix) {
        return new ArrayList<>(candidateController.filterByPrefix(prefix));
    }

    public Candidate getCandidate(String candidateID) throws MyException
    {
        return candidateController.getElement(candidateID);
    }

}
