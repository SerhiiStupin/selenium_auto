package com.auto.APITests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiPet {
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private ApiOwner apiOwner;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("visits")
    @Expose
    private List<Visit> visits = null;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiOwner getApiOwner() {
        return apiOwner;
    }

    public void setOwner(ApiOwner apiOwner) {
        this.apiOwner = apiOwner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
