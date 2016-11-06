package repository;

import domain.HasID;
import helpers.loader.ILoader;
import helpers.saver.ISaver;
import repository.exceptions.RepositoryException;

import java.util.Collection;

/**
 * Created by andrei on 11/2/2016.
 */
public class FileRepository<ID, Element extends HasID<ID>> extends AbstractRepository<ID, Element> implements IRepository<ID, Element> {

    private String fileName;
    private ILoader<Element> loader;
    private ISaver<Element> saver;

    public FileRepository(String fileName, ILoader<Element> loader, ISaver<Element> saver) {
        super();
        this.fileName = fileName;
        this.loader = loader;
        this.saver = saver;
        load();
    }

    @Override
    public void addElement(Element element) throws RepositoryException {
        super.addElement(element);
        this.save();
    }

    @Override
    public Element removeElement(ID ID) throws RepositoryException {
        Element e = super.removeElement(ID);
        this.save();
        return e;
    }

    @Override
    public void updateElement(ID ID, Element updatedElement) throws RepositoryException {
        super.updateElement(ID, updatedElement);
        this.save();
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

    private void load() {
        try {
            addCollection(loader.load(fileName));
        } catch (RepositoryException e) {
            items.clear();
            e.printStackTrace();
        }
    }

    private void save(){
        saver.save(super.getAll(),fileName);
    }
}
