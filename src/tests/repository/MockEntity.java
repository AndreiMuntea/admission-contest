package tests.repository;

import domain.HasID;

/**
 * Created by andrei on 11/3/2016.
 */
public class MockEntity implements HasID <String>{
    private String ID;
    private Integer field_one;
    private Double field_two;

    public Integer getField_one() {
        return field_one;
    }

    public void setField_one(Integer field_one) {
        this.field_one = field_one;
    }

    public Double getField_two() {
        return field_two;
    }

    public void setField_two(Double field_two) {
        this.field_two = field_two;
    }

    public MockEntity()
    {
        this("",0,0.0);
    }

    public MockEntity(String ID, Integer field_one, Double field_two)
    {
        this.ID = ID;
        this.field_one = field_one;
        this.field_two = field_two;
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MockEntity)) return false;

        MockEntity that = (MockEntity) o;

        if (ID != null ? !ID.equals(that.ID) : that.ID != null) return false;
        if (field_one != null ? !field_one.equals(that.field_one) : that.field_one != null) return false;
        return field_two != null ? field_two.equals(that.field_two) : that.field_two == null;
    }

}
