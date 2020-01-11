package com.auto.PageObjectTests;

public class Pet {
    private String name;
    private String birthDate;
    private String Type;

    public Pet() {
    }

    public Pet(String name, String birthDate, String type) {
        this.name = name;
        this.birthDate = birthDate;
        Type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
