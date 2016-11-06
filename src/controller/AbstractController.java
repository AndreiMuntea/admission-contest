package controller;

import controller.exceptions.ControllerException;
import repository.IRepository;
import utils.MyException;
import validator.IValidator;

import java.util.Collection;

/**
 * Created by andrei on 11/6/2016.
 */
public abstract class AbstractController<ID, E> implements IController<ID, E> {

    protected IValidator<E> validator;
    protected IRepository<ID, E> repository;

    public AbstractController(IValidator<E> validator, IRepository<ID, E> repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public IValidator<E> getValidator() {
        return validator;
    }

    public void setValidator(IValidator<E> validator) {
        this.validator = validator;
    }

    @Override
    public void addElement(String... args) throws MyException {
        E element = formatElement(args);
        validator.validate(element);
        repository.addElement(element);
    }

    @Override
    public E removeElement(String ID) throws MyException {
        ID elementID = formatID(ID);
        return repository.removeElement(elementID);
    }


    @Override
    public E getElement(String ID) throws MyException {
        ID elementID = formatID(ID);
        return repository.getElement(elementID);
    }

    @Override
    public Collection<E> getAll() throws MyException{
        return repository.getAll();
    }

    @Override
    public void updateElement(String ID, String... args) throws MyException{
        ID elementID = formatID(ID);
        E updatedElement = formatElement(args);
        validator.validate(updatedElement);
        repository.updateElement(elementID, updatedElement);
    }

    public abstract ID formatID(String ID) throws ControllerException;

    public abstract E formatElement(String... args) throws ControllerException;
}
