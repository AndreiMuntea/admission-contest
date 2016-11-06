package repository;

import domain.HasID;
import repository.exceptions.RepositoryException;

import java.util.Collection;

/**
 * Created by andrei on 11/2/2016.
 */
public class InMemoryRepository<ID, Element extends HasID<ID>> extends AbstractRepository<ID, Element> implements IRepository<ID, Element> {

    public InMemoryRepository() {
        super();
    }

    @Override
    public void addElement(Element element) throws RepositoryException {
        super.addElement(element);
    }

    @Override
    public Element removeElement(ID ID) throws RepositoryException {
        return super.removeElement(ID);
    }

    @Override
    public void updateElement(ID ID, Element updatedElement) throws RepositoryException {
        super.updateElement(ID, updatedElement);
    }

    @Override
    public Element getElement(ID ID) throws RepositoryException {
        return super.getElement(ID);
    }

    @Override
    public Boolean exists(ID ID) {
        return super.exists(ID);
    }

    @Override
    public Collection<Element> getAll() {
        return super.getAll();
    }

    @Override
    public void addCollection(Collection<Element> elements) throws RepositoryException {
        super.addCollection(elements);
    }

    @Override
    public Integer countElements() {
        return super.countElements();
    }
}
