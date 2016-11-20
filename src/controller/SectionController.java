package controller;

import controller.exceptions.ControllerException;
import domain.Section;
import repository.IRepository;
import utils.MyException;
import validator.IValidator;

import java.util.Collection;

/**
 * Created by andrei on 11/6/2016.
 */
public class SectionController extends AbstractController<String, Section> {

    public SectionController(IValidator<Section> validator, IRepository<String, Section> repository) {
        super(validator, repository);
    }

    @Override
    public void addElement(String... args) throws MyException {
        super.addElement(args);
    }

    @Override
    public Section removeElement(String ID) throws MyException {
        return super.removeElement(ID);
    }

    @Override
    public Section getElement(String ID) throws MyException {
        return super.getElement(ID);
    }

    @Override
    public Collection<Section> getAll() {
        return super.getAll();
    }

    @Override
    public void updateElement(String ID, String... args) throws MyException {
        super.updateElement(ID, args);
    }

    @Override
    public String formatID(String ID) throws ControllerException {
        return ID;
    }

    @Override
    public Section formatElement(String... args) throws ControllerException {

        String sectionID;
        String sectionName;
        Integer availableSlots;

        if (args.length != 3) throw new ControllerException("Invalid number of parameters!\n");

        try{
            sectionID = args[0];
            sectionName = args[1];
            availableSlots = Integer.parseInt(args[2]);
        }catch(NumberFormatException e){
            throw new ControllerException("Invalid parameters type " + e.getMessage() + "\n");
        }
        return new Section(sectionID, sectionName, availableSlots);
    }
}
