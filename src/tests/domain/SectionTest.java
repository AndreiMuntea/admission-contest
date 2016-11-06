package tests.domain;

import domain.Section;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andrei on 11/3/2016.
 */
public class SectionTest {
    @Test
    public void getID() throws Exception {
        Section testSection1 = new Section("ID1", "name1", 40);
        Section testSection2 = new Section();

        assertEquals(testSection1.getID(),"ID1");
        assertEquals(testSection2.getID(),"");
    }

    @Test
    public void getName() throws Exception {
        Section testSection1 = new Section("ID1", "name1", 40);
        Section testSection2 = new Section();

        assertEquals(testSection1.getName(),"name1");
        assertEquals(testSection2.getName(),"");
    }

    @Test
    public void setName() throws Exception {
        Section testSection1 = new Section("ID1", "name1", 40);

        assertEquals(testSection1.getName(),"name1");
        testSection1.setName("name2");
        assertEquals(testSection1.getName(),"name2");
    }

    @Test
    public void getAvailableSlots() throws Exception {
        Section testSection1 = new Section("ID1", "name1", 40);
        Section testSection2 = new Section();

        assertEquals(testSection1.getAvailableSlots(),new Integer(40));
        assertEquals(testSection2.getAvailableSlots(),new Integer(0));
    }

    @Test
    public void setAvailableSlots() throws Exception {
        Section testSection1 = new Section("ID1", "name1", 40);

        assertEquals(testSection1.getAvailableSlots(),new Integer(40));
        testSection1.setAvailableSlots(50);
        assertEquals(testSection1.getAvailableSlots(), new Integer(50));
    }

    @Test
    public void testToString() throws Exception {
        Section testSection1 = new Section("ID1", "name1", 40);

        assertEquals(testSection1.toString(),"Section{" +
                "ID='" + testSection1.getID() + '\'' +
                ", name='" + testSection1.getName() + '\'' +
                ", availableSlots=" + testSection1.getAvailableSlots() +
                '}');
    }

    @Test
    public void equals() throws Exception {
        Section testSection0 = new Section();
        Section testSection1 = new Section("ID1", "name1", 40);
        Section testSection2 = new Section("ID1","name2", 100);

        assertEquals(testSection1, testSection1);
        assertNotEquals(testSection1, 3);
        assertNotEquals(testSection1, testSection0);
        assertNotEquals(testSection1, testSection2);

        testSection2.setName("name1");
        assertNotEquals(testSection1, testSection2);

        testSection2.setAvailableSlots(40);
        assertEquals(testSection1, testSection2);
    }

}