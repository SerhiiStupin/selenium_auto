package com.auto.APITests.Owner;

import com.auto.PageObjectTests.Pet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "address",
        "city",
        "firstName",
        "id",
        "lastName",
        "pets",
        "telephone"
})
public class Owner {
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("pets")
    private List<Pet> pets = null;
    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("id")
    public String getId() {
        return String.valueOf(id);
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("pets")
    public List<Pet> getPets() {
        return pets;
    }

    @JsonProperty("pets")
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @JsonProperty("telephone")
    public String getTelephone() {
        return telephone;
    }

    @JsonProperty("telephone")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(address, owner.address) &&
                Objects.equals(city, owner.city) &&
                Objects.equals(firstName, owner.firstName) &&
                Objects.equals(id, owner.id) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(pets, owner.pets) &&
                Objects.equals(telephone, owner.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, firstName, id, lastName, pets, telephone);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", pets=" + pets +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
