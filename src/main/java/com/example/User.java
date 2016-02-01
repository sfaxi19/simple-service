package com.example;

/**
 * Created by sfaxi19 on 29.11.2015.
 */
public class User {

    private String name;
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

    public OutUser getUser(){
        return new OutUser(name,surname);
    }

}

