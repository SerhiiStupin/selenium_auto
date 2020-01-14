package com.auto.APITests.Delete;

import com.auto.PageObjectTests.Owner;
import com.auto.PageObjectTests.Pet;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApiOwner {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<Pet> pets = null;
    private int id;

    public ApiOwner(){

    }
    public ApiOwner(String firstName, String lastName, String address, String city, String telephone, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


    @Override
    public String toString() {
        return "ApiOwner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pets=" + pets +
                ", id=" + id +
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
//
//    @SerializedName("address")
//    @Expose
//    private String address;
//    @SerializedName("city")
//    @Expose
//    private String city;
//    @SerializedName("firstName")
//    @Expose
//    private String firstName;
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("lastName")
//    @Expose
//    private String lastName;
//    @SerializedName("pets")
//    @Expose
//    private List<Pet> pets = null;
//    @SerializedName("telephone")
//    @Expose
//    private String telephone;
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public List<Pet> getPets() {
//        return pets;
//    }
//
//    public void setPets(List<Pet> pets) {
//        this.pets = pets;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
////    public ApiOwner(String address, String city, String firstName, Integer id, String lastName, String telephone) {
////        this.address = address;
////        this.city = city;
////        this.firstName = firstName;
////        this.id = id;
////        this.lastName = lastName;
////        this.telephone = telephone;
////    }
}