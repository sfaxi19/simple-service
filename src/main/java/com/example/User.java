package com.example;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by sfaxi19 on 29.11.2015.
 */
public class User {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Surname")
    private String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    @JsonIgnore
    public User getUser(){
        return this;
    }

    public boolean equals(User user){
        return ((this.name.equals(user.name))
            &&(this.surname.equals(user.surname)));
    }

}

