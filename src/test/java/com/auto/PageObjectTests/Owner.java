package com.auto.PageObjectTests;

import java.util.Objects;

public class Owner {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private String pets;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPets() {
        return pets;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pets='" + pets + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(getFirstName(), owner.getFirstName()) &&
                Objects.equals(getLastName(), owner.getLastName()) &&
                Objects.equals(getAddress(), owner.getAddress()) &&
                Objects.equals(getCity(), owner.getCity()) &&
                Objects.equals(getTelephone(), owner.getTelephone()) &&
                Objects.equals(getPets(), owner.getPets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAddress(), getCity(), getTelephone(), getPets());
    }
}
