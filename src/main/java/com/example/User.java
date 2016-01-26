package com.example;

/**
 * Created by sfaxi19 on 29.11.2015.
 */
public class User {

    private String name;
    private String surname;

    private class OutUser{

        public String name;
        public String surname;

        public OutUser(){
            this.name = User.this.name;
            this.surname = User.this.surname;
        }

    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name){
        this.name = name;
    }

    public Object getUser(){
        OutUser outUser = new OutUser();
        return outUser;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
}
