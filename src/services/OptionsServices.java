package services;

import controller.CandidateController;
import controller.OptionController;
import controller.SectionController;
import domain.Candidate;
import domain.Option;
import domain.Section;
import utils.MyException;
import utils.obs.AbstractObservable;
import utils.obs.Observable;
import utils.obs.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 12/1/2016.
 */
public class OptionsServices extends AbstractObservable<Option> implements Observer{
    private OptionController optionController;
    private final CandidateController candidateController;
    private final SectionController sectionController;

    public OptionsServices(OptionController optionController, CandidateController candidateController, SectionController sectionController) {
        this.optionController = optionController;
        this.candidateController = candidateController;
        this.sectionController = sectionController;
    }


    public void registerCandidate(String candidateID, String sectionID) throws MyException
    {
        optionController.addOption(candidateID, sectionID);
        notifyObservers();

    }

    public void removeAllCandidatesEntries(String candidateID)
    {
        try {
            optionController.removeAllEntriesForCandidate(candidateID);
            notifyObservers();
        }catch (Exception e)
        {

        }
    }

    public void removeAllSectionsEntry(String sectionID)
    {
        try{
            optionController.removeAllEntriesForSection(sectionID);
            notifyObservers();
        }catch(Exception e)
        {

        }
    }

    public List<Section> getSectionsForCandidate(String candidateID) {
        List<Option> sectionsID = new ArrayList<>();
        List<Section> sectionsNames = new ArrayList<>();
        try {
            sectionsID.addAll(optionController.filterOptionsByCandidates(candidateID));
            for(Option option : sectionsID) sectionsNames.add(sectionController.getElement(option.getSectionID()));
        }catch (MyException e)
        {
            sectionsID.clear();
            sectionsNames.clear();
        }
        return sectionsNames;
    }

    public List<Candidate> getCandidatesForSection(String sectionID)
    {
        List<Option> candidatesID = new ArrayList<>();
        List<Candidate> candidatesNames = new ArrayList<>();
        try {
            candidatesID.addAll(optionController.filterOptionsBySection(sectionID));
            for(Option option : candidatesID) candidatesNames.add(candidateController.getElement(option.getCandidateID().toString()));
        }catch (MyException e)
        {
            candidatesID.clear();
            candidatesNames.clear();
        }
        return candidatesNames;
    }

    @Override
    public void update(Observable observable) {

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof SectionServices)
        {
            removeAllSectionsEntry((String)o);
        }
        if (observable instanceof  CandidateServices)
        {
            removeAllCandidatesEntries((String)o);
        }
    }
}