package tests.repository;


import org.junit.Test;
import repository.IRepository;
import repository.InMemoryRepository;
import repository.exceptions.RepositoryException;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by andrei on 11/3/2016.
 */
public class AbstractRepositoryTest {
    @Test
    public void addElement() throws Exception {
        IRepository<String, MockEntity> repository = new InMemoryRepository<>();

        MockEntity a = new MockEntity();
        MockEntity b = new MockEntity("ID1", 1, 0.1);
        MockEntity c = new MockEntity("ID2", 1, 0.1);
        MockEntity d = new MockEntity();

        assertEquals(repository.countElements(), new Integer(0));

        repository.addElement(a);
        assertEquals(repository.countElements(), new Integer(1));

        repository.addElement(b);
        assertEquals(repository.countElements(), new Integer(2));

        repository.addElement(c);
        assertEquals(repository.countElements(), new Integer(3));


        try {
            repository.addElement(d);
            assertTrue(false);
        } catch (RepositoryException e) {
            assertTrue(true);
        }

        assertEquals(repository.countElements(), new Integer(3));

    }

    @Test
    public void removeElement() throws Exception {
        IRepository<String, MockEntity> repository = new InMemoryRepository<>();

        MockEntity a = new MockEntity();
        MockEntity b = new MockEntity("ID1", 1, 0.1);
        MockEntity c = new MockEntity("ID2", 1, 0.1);

        repository.addElement(a);
        repository.addElement(b);
        repository.addElement(c);

        assertEquals(repository.countElements(), new Integer(3));
        repository.removeElement("ID1");
        assertEquals(repository.countElements(), new Integer(2));

        repository.removeElement("ID2");
        assertEquals(repository.countElements(), new Integer(1));

        try {
            repository.removeElement("ID2");
            assertTrue(false);
        } catch (RepositoryException e) {
            assertTrue(true);
        }

        assertEquals(repository.countElements(), new Integer(1));

        repository.removeElement("");
        assertEquals(repository.countElements(), new Integer(0));

    }

    @Test
    public void updateElement() throws Exception {
        IRepository<String, MockEntity> repository = new InMemoryRepository<>();

        MockEntity a = new MockEntity();
        MockEntity b = new MockEntity("ID1", 1, 0.1);
        MockEntity c = new MockEntity("ID2", 1, 0.1);
        MockEntity d = new MockEntity("ID1", 2, 0.2);

        repository.addElement(b);
        repository.addElement(c);

        try {
            repository.updateElement("ID1", c);
            assertTrue(false);
        } catch (RepositoryException e) {
            assertTrue(true);
        }

        try {
            repository.updateElement("", a);
            assertTrue(false);
        } catch (RepositoryException e) {
            assertTrue(true);
        }

        try {
            repository.updateElement("ID1", d);
            assertTrue(true);
        } catch (RepositoryException e) {
            assertTrue(false);
        }

    }

    @Test
    public void getElement() throws Exception {
        IRepository<String, MockEntity> repository = new InMemoryRepository<>();

        MockEntity a = new MockEntity("ID1", 1, 0.1);
        MockEntity b = new MockEntity("ID1", 1, 0.1);

        repository.addElement(a);

        try {
            MockEntity found = repository.getElement("");
            assertTrue(false);
        } catch (RepositoryException e) {
            assertTrue(true);
        }

        assertEquals(repository.getElement("ID1"), b);
    }


    @Test
    public void getAll() throws Exception {
        IRepository<String, MockEntity> repository = new InMemoryRepository<>();

        MockEntity a = new MockEntity();
        MockEntity b = new MockEntity("ID1", 1, 0.1);
        MockEntity c = new MockEntity("ID2", 1, 0.1);

        repository.addElement(a);
        repository.addElement(b);
        repository.addElement(c);

        Collection<MockEntity> items = repository.getAll();

        assertEquals(items.size(), 3);
        assertTrue(items.contains(a));
        assertTrue(items.contains(b));
        assertTrue((items.contains(c)));
    }

    @Test
    public void addCollection() throws Exception {
        IRepository<String, MockEntity> repository = new InMemoryRepository<>();

        MockEntity a = new MockEntity();
        MockEntity b = new MockEntity("ID1", 1, 0.1);
        MockEntity c = new MockEntity("ID2", 1, 0.1);
        MockEntity d = new MockEntity("ID2", 1, 0.1);

        Collection<MockEntity> items = new ArrayList<>();
        items.add(a);
        items.add(b);
        items.add(c);
        items.add(d);
        try {
            repository.addCollection(items);
            assertTrue(false);
        } catch (RepositoryException e) {
            assertTrue(true);
        }

    }

}