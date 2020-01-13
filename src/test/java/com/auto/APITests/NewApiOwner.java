package com.auto.APITests;

import com.auto.PageObjectTests.Pet;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class NewApiOwner {
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
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
        public Integer getId() {
            return id;
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

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
