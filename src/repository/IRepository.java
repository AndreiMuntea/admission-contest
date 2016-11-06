package repository;

import repository.exceptions.RepositoryException;

import java.util.Collection;

/**
 * Created by andrei on 11/2/2016.
 */
public interface IRepository<ID,Element> {
    /**
     * Adds an element in repository.
     * @param element - an Element. The element to be added.
     * @throws RepositoryException - if another element with the same ID already exists.
     */
    void addElement(Element element) throws RepositoryException;

    /**
     * Removes an element from repository.
     * @param ID - the element's ID
     * @return - the element with the given ID.
     * @throws RepositoryException - if the element doesn't exists.
     */
    Element removeElement(ID ID) throws RepositoryException;

    /**
     * Updates an element.
     * @param ID - the element's ID.
     * @param updatedElement - the new definition of the element.
     * @throws RepositoryException - if the ID was changed or the element doesn't exist.
     */
    void updateElement(ID ID, Element updatedElement) throws RepositoryException;

    /**
     * Get an element.
     * @param ID - the element's ID.
     * @return the element.
     * @throws RepositoryException if the element doesn't exist
     */
    Element getElement(ID ID) throws RepositoryException;

    /**
     * Check if an element exist or no.
     * @param ID - the element unique ID.
     * @return true if there is an element with the given ID, false otherwise.
     */
    Boolean exists(ID ID);

    /**
     * Get a list of all elements from repository.
     * @return - a Collection<Element>
     */
    Collection<Element> getAll();

    /**
     * Add a list of elements in repository
     * @param elements - a Collection<Element> the list of elements to be added.
     * @throws RepositoryException - if the list contains duplicates.
     */
    void addCollection(Collection<Element> elements) throws RepositoryException;

    /**
     * Count the elements from repository.
     * @return  an Integer. The number of elements currently in repo.
     */
    Integer countElements();
}
