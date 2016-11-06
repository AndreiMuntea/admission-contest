package domain;

import java.io.Serializable;

/**
 * Created by andrei on 11/2/2016.
 */
public class Candidate implements HasID<Integer>, Serializable {

    private Integer ID;
    private String name;
    private String address;
    private String phoneNumber;
    private Double grade;

    /**
     * Default constructor.
     */
    public Candidate()
    {
        this(0,"","","",0.0);
    }

    /**
     *
     * @param ID - an Integer. The candidate's given ID.
     * @param name - a String. The candidate's given name.
     * @param address - a String. The candidate's given address.
     * @param phoneNumber - a String. The candidate's given phone number.
     * @param grade - a Double. The candidate's given grade.
     */
    public Candidate(Integer ID, String name, String address, String phoneNumber, Double grade)
    {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.grade = grade;
    }

    /**
     *
     * @return String - name of the candidate.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - a String. The new name of the candidate.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return String - address of the candidate.
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address - a String. The new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return String - the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber - a String. The number of the candidate.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return Double - the candidate's grade.
     */
    public Double getGrade() {
        return grade;
    }

    /**
     *
     * @param grade - a Double. The new grade.
     */
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    /**
     *
     * @return an Integer. The Candidate's ID.
     */
    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;

        Candidate candidate = (Candidate) o;

        if (!ID.equals(candidate.ID)) return false;
        if (!name.equals(candidate.name)) return false;
        if (!address.equals(candidate.address)) return false;
        if (!phoneNumber.equals(candidate.phoneNumber)) return false;
        return grade.equals(candidate.grade);
    }


    @Override
    public String toString() {
        return "Candidate{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", grade=" + grade +
                '}';
    }

}
