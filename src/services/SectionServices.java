package services;

import controller.SectionController;
import domain.Section;
import utils.MyException;
import utils.obs.AbstractObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 12/1/2016.
 */
public class SectionServices extends AbstractObservable<Section> {

    private SectionController sectionController;

    public SectionServices(SectionController sectionController)
    {
        this.sectionController = sectionController;
    }

    public void addSection(String sectionID, String sectionName, String sectionSlots) throws MyException
    {
        sectionController.addElement(sectionID, sectionName, sectionSlots);
        notifyObservers();
    }

    public void updateSection(String sectionID, String sectionName, String sectionSlots) throws MyException
    {
        sectionController.updateElement(sectionID, sectionName,sectionSlots);
        notifyObservers();
    }

    public void removeSection(String sectionID) throws MyException
    {
        sectionController.removeElement(sectionID);
        notifyObservers();
        notifyObservers(sectionID);
    }

    public List<Section> getAll()
    {
        return new ArrayList<>(sectionController.getAll());
    }

    public List<Section> filterByPrefix(String prefix)
    {
        return sectionController.filterByPrefix(prefix);
    }

    public List<Section> filterByMinimumSlots(String slots) throws MyException
    {
        return sectionController.filterByMinimumSlots(slots);
    }
}
