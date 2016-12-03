package controller;

import controller.exceptions.ControllerException;
import domain.Candidate;
import domain.DTO.TopSectionsByAverageDTO;
import domain.DTO.TopSectionsByNumberDTO;
import domain.Option;
import domain.Section;
import repository.IRepository;
import utils.MyException;
import utils.Pair;
import utils.obs.AbstractObservable;

import java.util.ArrayList;
import java.util.Comparator;
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
            Predicate<Option> filterByCandidate = o -> o.getCandidateID().equals(IDcandidate);
            return filterList(allOptions, filterByCandidate);

        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid ID for candidate!\n");
        }
    }

    public List<Option> filterOptionsBySection(String sectionID) throws MyException {
        List<Option> allOptions = new ArrayList<>(optionRepository.getAll());
        Predicate<Option> filterBySection = o -> o.getSectionID().equals(sectionID);
        return filterList(allOptions, filterBySection);
    }

    public void removeAllEntriesForCandidate(String candidateID) throws MyException {
        List<Option> candidatesOptions = this.filterOptionsByCandidates(candidateID);
        for (Option option : candidatesOptions) optionRepository.removeElement(option.getID());
    }

    public void removeAllEntriesForSection(String sectionID) throws MyException {
        List<Option> sectionsList = this.filterOptionsBySection(sectionID);
        for (Option option : sectionsList) optionRepository.removeElement(option.getID());
    }

    public Integer getNumberOfCandidatesForSection(String sectionID) throws MyException {
        List<Option> sectionsList = this.filterOptionsBySection(sectionID);
        return sectionsList.size();
    }

    public Double getAveragePerSection(String sectionID) throws MyException{
        List<Option> candidatesList = this.filterOptionsBySection(sectionID);
        Double average = 0.0;
        if (candidatesList.size() == 0) return 0.0;
        for(Option o : candidatesList) average += candidateRepository.getElement(o.getCandidateID()).getGrade();
        return average / candidatesList.size();
    }

    public List<TopSectionsByNumberDTO> getMostWantedSections(String NumberOfSections) throws MyException {
        Integer numberOfSections;
        try {
            numberOfSections = Integer.parseInt(NumberOfSections);
            if (numberOfSections < 1) throw new ControllerException("There should be at least 1 section!\n");
        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid argument " + NumberOfSections + "!\n");
        }
        List<TopSectionsByNumberDTO> list = new ArrayList<>();
        List<Section> allSections = new ArrayList<>(sectionRepository.getAll());
        for (Section section : allSections) {
            Integer candidates = getNumberOfCandidatesForSection(section.getID());
            TopSectionsByNumberDTO reportSection = new TopSectionsByNumberDTO(section.getName(), candidates);
            list.add(reportSection);
        }
        Comparator<TopSectionsByNumberDTO> cmp = (TopSectionsByNumberDTO left, TopSectionsByNumberDTO right) -> right.getNumberOfCandidates().compareTo(left.getNumberOfCandidates());
        list.sort(cmp);

        return list.subList(0, numberOfSections - 1);
    }

    public List<TopSectionsByAverageDTO> getSectionsWithHighestAverageGrade(String NumberOfSections) throws MyException
    {
        Integer numberOfSections;
        try {
            numberOfSections = Integer.parseInt(NumberOfSections);
            if (numberOfSections < 1) throw new ControllerException("There should be at least 1 section!\n");
        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid argument " + NumberOfSections + "!\n");
        }
        List<TopSectionsByAverageDTO> list = new ArrayList<>();
        List<Section> allSections = new ArrayList<>(sectionRepository.getAll());
        for (Section section : allSections) {
            Double average = getAveragePerSection(section.getID());
            TopSectionsByAverageDTO reportSection = new TopSectionsByAverageDTO(section.getName(), average);
            if (average.compareTo(0.0) > 0 ) list.add(reportSection);
        }
        Comparator<TopSectionsByAverageDTO> cmp = (TopSectionsByAverageDTO left, TopSectionsByAverageDTO right) -> right.getAverage().compareTo(left.getAverage());
        list.sort(cmp);

        return list.subList(0, numberOfSections - 1);
    }



}
