package controller;

import controller.exceptions.ControllerException;
import domain.Candidate;
import repository.IRepository;
import utils.MyException;
import validator.IValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by andrei on 11/6/2016.
 */
public class CandidateController extends AbstractController<Integer, Candidate> {

    public CandidateController(IValidator<Candidate> validator, IRepository<Integer, Candidate> repository) {
        super(validator, repository);
    }

    @Override
    public void addElement(String... args) throws MyException {
        super.addElement(args);
    }

    @Override
    public Candidate removeElement(String ID) throws MyException {
        return super.removeElement(ID);
    }

    @Override
    public Candidate getElement(String ID) throws MyException {
        return super.getElement(ID);
    }

    @Override
    public Collection<Candidate> getAll() {
        return super.getAll();
    }

    @Override
    public void updateElement(String... args) throws MyException {
        super.updateElement(args);
    }


    @Override
    public Integer formatID(String ID) throws ControllerException {
        Integer candidateID;
        try {
            candidateID = Integer.parseInt(ID);
        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid parameters type " + e.getMessage() + "\n");
        }
        return candidateID;
    }

    @Override
    public Candidate formatElement(String... args) throws ControllerException {
        Integer ID;
        String name;
        String address;
        String phoneNumber;
        Double grade;

        if (args.length != 5) {
            throw new ControllerException("Invalid number of parameters!\n");
        }

        try {
            ID = Integer.parseInt(args[0]);
            name = args[1];
            address = args[2];
            phoneNumber = args[3];
            grade = Double.parseDouble(args[4]);
        } catch (NumberFormatException e) {
            throw new ControllerException("Invalid parameters type " + e.getMessage() + "\n");
        }

        return new Candidate(ID, name, address, phoneNumber, grade);
    }

    public List<Candidate> filterByPrefix(String prefix)
    {
        List<Candidate> list = new ArrayList<>(super.getAll());
        if (prefix.isEmpty()) return list;

        Predicate<Candidate> predicate = (c) -> c.getName().startsWith(prefix);
        return super.filterList(list,predicate);
    }

}
