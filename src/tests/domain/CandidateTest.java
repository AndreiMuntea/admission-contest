package tests.domain;

import domain.Candidate;

import static org.junit.Assert.*;

/**
 * Created by andrei on 11/3/2016.
 */
public class CandidateTest {
    @org.junit.Test
    public void getName() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);
        Candidate testCandidate2 = new Candidate();

        assertEquals(testCandidate1.getName(),"name1");
        assertEquals(testCandidate2.getName(),"");
    }

    @org.junit.Test
    public void setName() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);

        assertEquals(testCandidate1.getName(),"name1");
        testCandidate1.setName("name2");
        assertEquals(testCandidate1.getName(),"name2");
    }

    @org.junit.Test
    public void getAddress() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);
        Candidate testCandidate2 = new Candidate();

        assertEquals(testCandidate1.getAddress(),"address1");
        assertEquals(testCandidate2.getAddress(),"");
    }

    @org.junit.Test
    public void setAddress() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);

        assertEquals(testCandidate1.getAddress(),"address1");
        testCandidate1.setAddress("address2");
        assertEquals(testCandidate1.getAddress(),"address2");
    }

    @org.junit.Test
    public void getPhoneNumber() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);
        Candidate testCandidate2 = new Candidate();

        assertEquals(testCandidate1.getPhoneNumber(),"0727126337");
        assertEquals(testCandidate2.getPhoneNumber(),"");
    }

    @org.junit.Test
    public void setPhoneNumber() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);

        assertEquals(testCandidate1.getPhoneNumber(),"0727126337");
        testCandidate1.setPhoneNumber("0725432129");
        assertEquals(testCandidate1.getPhoneNumber(),"0725432129");
    }

    @org.junit.Test
    public void getGrade() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);
        Candidate testCandidate2 = new Candidate();

        assertEquals(testCandidate1.getGrade(),new Double(5.2));
        assertEquals(testCandidate2.getGrade(),new Double(0.0));
    }

    @org.junit.Test
    public void setGrade() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);

        assertEquals(testCandidate1.getGrade(),new Double(5.2));
        testCandidate1.setGrade(9.2);
        assertEquals(testCandidate1.getGrade(),new Double(9.2));
    }

    @org.junit.Test
    public void getID() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);
        Candidate testCandidate2 = new Candidate();

        assertEquals(testCandidate1.getID(),new Integer(1));
        assertEquals(testCandidate2.getID(),new Integer(0));
    }

    @org.junit.Test
    public void equals() throws Exception {
        Candidate testCandidate0 = new Candidate();
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);
        Candidate testCandidate2 = new Candidate(1,"name2","address2","0727126339", 9.3);

        assertEquals(testCandidate1, testCandidate1);
        assertNotEquals(testCandidate1, 3);
        assertNotEquals(testCandidate1, testCandidate0);
        assertNotEquals(testCandidate1, testCandidate2);

        testCandidate2.setName("name1");
        assertNotEquals(testCandidate1, testCandidate2);

        testCandidate2.setAddress("address1");
        assertNotEquals(testCandidate1, testCandidate2);

        testCandidate2.setPhoneNumber("0727126337");
        assertNotEquals(testCandidate1, testCandidate2);

        testCandidate2.setGrade(5.2);
        assertEquals(testCandidate1, testCandidate2);
    }

    @org.junit.Test
    public void testToString() throws Exception {
        Candidate testCandidate1 = new Candidate(1,"name1","address1","0727126337",5.2);

        assertEquals(testCandidate1.toString(), "Candidate{" +
                                    "ID=" + testCandidate1.getID() +
                                    ", name='" + testCandidate1.getName() + '\'' +
                                    ", address='" + testCandidate1.getAddress() + '\'' +
                                    ", phoneNumber='" + testCandidate1.getPhoneNumber() + '\'' +
                                    ", grade=" + testCandidate1.getGrade() + '}');
    }


}