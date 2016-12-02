package controller;

import controller.exceptions.ControllerException;
import domain.Candidate;
import domain.Option;
import domain.Section;
import repository.IRepository;
import utils.MyException;
import utils.Pair;
import utils.obs.AbstractObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andrei on 12/1/2016.
 */
public class OptionController extends AbstractObservable<Option> {
    private final IRepository<Integer, Candidate> candidateRepository;
    private final IRepository<String, Section> sectionRepository;
    private IRepository<Pair<Integer, String>, Option> optionRepository;

    public OptionController(IRepository<Integer, Candidate> candidateRepository,
                            IRepository<String, Section> sectionRepository,
                            IRepository<Pair<Integer, String>, Option> optionRepository) {
        this.candidateRepository = candidateRepository;
        this.sectionRepository = sectionRepository;
        this.optionRepository = optionRepository;
    }

    public void addOption(String candidateID, String sectionID) throws MyException {
        try {
            Integer IDCandidate = Integer.parseInt(candidateID);
            if (candidateRepository.exists(IDCandidate) && sectionRepository.exists(sectionID)) {
                Pair<Integer, String> option = new Pair<>(IDCandidate, sectionID);
                optionRepository.addElement(new Option(option));
            }
        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid ID for candidate!\n");
        }
    }

    public void removeOption(String candidateID, String sectionID) throws MyException {
        try {
            Integer IDcandidate = Integer.parseInt(candidateID);
            if (candidateRepository.exists(IDcandidate) && sectionRepository.exists(sectionID)) {
                Pair<Integer, String> option = new Pair<>(IDcandidate, sectionID);
                optionRepository.removeElement(option);
            }
        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid ID for candidate!\n");
        }
    }

    public List<Option> filterList(List<Option> list, Predicate<Option> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<Option> filterOptionsByCandidates(String candidateID) throws MyException {
        try {
            Integer IDcandidate = Integer.parseInt(candidateID);
            List<Option> allOptions = new ArrayList<>(optionRepository.getAll());
            Predicate<Option> filterByCandidate = o->o.getCandidateID().equals(IDcandidate);
            return filterList(allOptions,filterByCandidate);

        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid ID for candidate!\n");
        }
    }

    public List<Option> filterOptionsBySection(String sectionID) throws MyException{
        List<Option> allOptions = new ArrayList<>(optionRepository.getAll());
        Predicate<Option> filterBySection = o->o.getSectionID().equals(sectionID);
        return filterList(allOptions, filterBySection);
    }

    public void removeAllEntriesForCandidate(String candidateID) throws MyException{
        List<Option> candidatesOptions = this.filterOptionsByCandidates(candidateID);
        for(Option option : candidatesOptions) optionRepository.removeElement(option.getID());
    }

    public void removeAllEntriesForSection(String sectionID) throws MyException{
        List<Option> sectionsList = this.filterOptionsBySection(sectionID);
        for(Option option : sectionsList) optionRepository.removeElement(option.getID());
    }


}
