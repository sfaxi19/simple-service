package com.example;

public class OutUser{

    public String name;
    public String surname;

    public OutUser(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public boolean equals(OutUser obj) {
        return (obj.name.equals(this.name) && obj.surname.equals(this.surname));
    }
}
