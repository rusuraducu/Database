package PetShop;

import java.sql.Date;

public class Pet {
    private String name;
    private Date birthdate;
    private char gender;
    private int type;
    private int personId;


    public Pet(String name, Date birthdate, char gender, int type, int personId) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.type = type;
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public char getGender() {
        return gender;
    }

    public int getType() {
        return type;
    }

    public int getPersonId() {
        return personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", type=" + type +
                ", personId=" + personId +
                '}';
    }
}
