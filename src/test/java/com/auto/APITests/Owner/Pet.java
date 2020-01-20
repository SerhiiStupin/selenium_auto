package com.auto.APITests.Owner;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "birthDate",
        "id",
        "name",
        "owner",
        "type",
        "visits"
})
public class Pet {
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("type")
    private Type type;
    @JsonProperty("visits")
    private List<Visit> visits = null;

    @JsonProperty("birthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Type type) {
        this.type = type;
    }

    @JsonProperty("visits")
    public List<Visit> getVisits() {
        return visits;
    }

    @JsonProperty("visits")
    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

//    public void setVisits(String date, String description, String id, String pet) {
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(birthDate, pet.birthDate) &&
                Objects.equals(id, pet.id) &&
                Objects.equals(name, pet.name) &&
                Objects.equals(owner, pet.owner) &&
                Objects.equals(type, pet.type) &&
                Objects.equals(visits, pet.visits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, id, name, owner, type, visits);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "birthDate='" + birthDate + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", type=" + type +
                ", visits=" + visits +
                '}';
    }
}