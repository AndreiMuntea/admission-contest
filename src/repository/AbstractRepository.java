package repository;

import domain.HasID;
import repository.exceptions.RepositoryChangedIDException;
import repository.exceptions.RepositoryDuplicateItemException;
import repository.exceptions.RepositoryException;
import repository.exceptions.RepositoryNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 11/2/2016.
 */
public class AbstractRepository<ID, Element extends HasID<ID>> implements IRepository<ID, Element> {

    protected Map<ID, Element> items;

    public AbstractRepository() {
        items = new HashMap<>();
    }

    @Override
    public void addElement(Element element) throws RepositoryException {
        ID elementID = element.getID();
        if (exists(elementID)) {
            throw new RepositoryDuplicateItemException("Item with ID " + elementID + "already exists!\n");
        }
        items.put(elementID, element);
    }

    @Override
    public Element removeElement(ID ID) throws RepositoryException {
        if (!exists(ID)) {
            throw new RepositoryNotFoundException("Item with ID " + ID + "doesn't exists!\n");
        }

        Element e = items.get(ID);
        items.remove(ID);
        return e;
    }

    @Override
    public void updateElement(ID ID, Element updatedElement) throws RepositoryException {
        if (ID != updatedElement.getID()) {
            throw new RepositoryChangedIDException("ID can't be changed!\n");
        }
        if (!exists(ID)) {
            throw new RepositoryNotFoundException("Item with ID " + ID + "doesn't exists!\n");
        }
        items.put(ID,updatedElement);
    }

    @Override
    public Element getElement(ID ID) throws RepositoryException {
        if (!exists(ID)) {
            throw new RepositoryNotFoundException("Item with ID " + ID + "doesn't exists!\n");
        }
        return items.get(ID);
    }

    @Override
    public Boolean exists(ID ID) {
        return items.containsKey(ID);
    }


    @Override
    public Collection<Element> getAll() {
        return items.values();
    }

    @Override
    public void addCollection(Collection<Element> elements)throws RepositoryException {
        for(Element e: elements){
            addElement(e);
        }
    }

    @Override
    public Integer countElements() {
        return items.size();
    }
}
