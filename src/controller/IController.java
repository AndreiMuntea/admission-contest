package controller;

import utils.MyException;

import java.util.Collection;

/**
 * Created by andrei on 11/6/2016.
 */
public interface IController<ID, E> {
    void addElement(String... args) throws MyException;
    E removeElement(String ID) throws MyException;
    E getElement(String ID) throws MyException;
    Collection<E> getAll() throws MyException;
    void updateElement(String ID, String... args) throws MyException;
}
