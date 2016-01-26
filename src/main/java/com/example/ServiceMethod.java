package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sfaxi19 on 29.11.2015.
 */
@RestController
public class ServiceMethod {

    private final HashMap<String, User> persons = new HashMap<>();
    private int id=0;

    @RequestMapping(method = RequestMethod.GET)
    Object getUsers(@RequestParam(value ="id",required = false)String id) {
        if(id!=null){
            return persons.get(id);
        }else {
            return persons;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    void createUser(@RequestParam(value = "name",required = false,
                                  defaultValue = "null")String name,
                    @RequestParam(value = "surname",required = false,
                                  defaultValue = "null") String surname) {
        User newUser;
        newUser = new User(name,surname);
        synchronized (persons) {
            persons.put(Integer.toString(id), newUser);
            System.out.println("create: id = " + id + "; Name = " + name
                    + "; Surname = " + surname);
            id++;
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    Map updateUser(String id,
                   @RequestParam(value = "name",required = false,
                                 defaultValue = "null")String name,
                   @RequestParam(value = "surname",required = false,
                                 defaultValue = "null") String surname) {
        persons.get(id).setName(name);
        persons.get(id).setSurname(surname);
        System.out.println("update: id = " + id + "; Name = " + name
                         + "; Surname = " + surname);
        return persons;
    }

    /* example request:
        curl --request PATCH localhost:8080 --data "id=0&surname=Ivanov" */
    @RequestMapping(method = RequestMethod.PATCH)
    Map updateUser2(String id,
                    @RequestParam(value = "surname",
                                  required = false)String surname,
                    @RequestParam(value = "name",required = false)String name) {
        if(name!=null) {
            persons.get(id).setName(name);
            System.out.println("path: id = " + id + "; name = " + name);
        }
        if(surname!=null) {
            persons.get(id).setSurname(surname);
            System.out.println("path: id = " + id + "; surname = " + surname);
        }
        return persons;
    }

    /* example request: curl --request DELETE localhost:8080?id=0 */
    @RequestMapping(method = RequestMethod.DELETE)
    Map deleteUser(String id){
        System.out.println("delete: id = " + id);
        persons.remove(id);
        return persons;
    }

}
