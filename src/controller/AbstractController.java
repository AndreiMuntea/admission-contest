package controller;

import controller.exceptions.ControllerException;
import repository.IRepository;
import utils.MyException;
import validator.IValidator;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by andrei on 11/6/2016.
 */
public abstract class AbstractController<ID, E>{

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

    public void addElement(String... args) throws MyException {
        E element = formatElement(args);
        validator.validate(element);
        repository.addElement(element);
    }

    public E removeElement(String ID) throws MyException {
        ID elementID = formatID(ID);
        return repository.removeElement(elementID);
    }


    public E getElement(String ID) throws MyException {
        ID elementID = formatID(ID);
        return repository.getElement(elementID);
    }

    public Collection<E> getAll(){
        return repository.getAll();
    }


    public List<E> filterList(List<E> list, Predicate<E> predicate)
    {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public void updateElement(String... args) throws MyException{
        ID elementID = formatID(args[0]);
        E updatedElement = formatElement(args);
        validator.validate(updatedElement);
        repository.updateElement(elementID, updatedElement);
    }

    public abstract ID formatID(String ID) throws ControllerException;

    public abstract E formatElement(String... args) throws ControllerException;
}
