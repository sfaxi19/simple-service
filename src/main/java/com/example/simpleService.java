package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class simpleService {
    public HashMap persons =  new HashMap();
    public class userClass
    {
        public String name;
        public String surname;
        public String phone;

        public userClass()
        {
            this.name = "null";
            this.phone = "null";
            this.surname = "null";
        }
    }

    public userClass newUser;

    @RequestMapping("/")
    Map home() {
        return persons;
    }

    @RequestMapping("/list")
    Map listView() {
        return persons;
    }

    @RequestMapping("/setUser")
    Map setName(String name) {
        int id = 0;
        newUser = new userClass();
        newUser.name = name;
        while (persons.get(id) != null)
            id++;
        persons.put(id, newUser);
        return persons;
    }

    @RequestMapping("/chgName")
    Map reName(int id, String name) {
        if (persons.get(id) != null) ((userClass) persons.get(id)).name = name;
        return persons;
    }

    @RequestMapping("/chgSurname")
    Map reSurname(int id,String surname) {
        if (persons.get(id) != null) ((userClass) persons.get(id)).surname = surname;
        return persons;
    }
    @RequestMapping("/chgPhone")
    Map rePhone(int id, String phone) {
        if (persons.get(id) != null) ((userClass) persons.get(id)).phone = phone;
        return persons;
    }


    @RequestMapping("/getUser")
    userClass getByID(int id) {
        return (userClass)persons.get(id);
    }

    @RequestMapping("/delUser")
    Map delByID(int id) {
        persons.remove(id);
        return persons;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(simpleService.class, args);
    }

}