package domain;

import java.io.Serializable;

/**
 * Created by andrei on 11/2/2016.
 */
public class Section implements HasID<String>, Serializable {


    private String ID;
    private String name;
    private Integer availableSlots;


    /**
     * Default constructor.
     */
    public Section() {
        this("", "", 0);
    }

    /**
     *
     * @param ID - a String. The section's given ID.
     * @param name - a String. The section's given name.
     * @param availableSlots - a String. The section's given number of slots.
     */
    public Section(String ID, String name, Integer availableSlots) {
        this.ID = ID;
        this.name = name;
        this.availableSlots = availableSlots;
    }

    /**
     *
     * @return a String. Section's ID.
     */
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     *
     * @return a String. The section's name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - a String. The section's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return an Integer. Then number of available slots of the section.
     */
    public Integer getAvailableSlots() {
        return availableSlots;
    }

    /**
     *
     * @param availableSlots - an Integer. The new number of available slots.
     */
    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "Section{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", availableSlots=" + availableSlots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;

        Section section = (Section) o;

        if (!ID.equals(section.ID)) return false;
        if (!name.equals(section.name)) return false;
        return availableSlots.equals(section.availableSlots);

    }
}
