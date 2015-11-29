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

    public HashMap<String, User> persons = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    Object userGET(@RequestParam(value ="id",required = false,
                    defaultValue = "non") String id) {
        if(!id.equals("non")){
            return persons.get(id);
        }else {
            return persons;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    Map userPOST(@RequestParam(value = "name",required = false,
                         defaultValue = "null")String name,
                 @RequestParam(value = "surname",required = false,
                         defaultValue = "null") String surname) {
        int id = 0;
        User newUser;
        newUser = new User();
        newUser.name = name;
        newUser.surname = surname;
        while (persons.get(Integer.toString(id)) != null)
            id++;
        persons.put(Integer.toString(id), newUser);
        System.out.println("POST: id = " + id + "; Name = " + name
                + "; Surname = " + surname);
        return persons;
    }

    @RequestMapping(method = RequestMethod.PUT)
    Map userPUT(String id,
                @RequestParam(value = "name",required = false,
                        defaultValue = "null")String name,
                @RequestParam(value = "surname",required = false,
                        defaultValue = "null") String surname) {
        User newUser = new User();
        newUser.name=name;
        newUser.surname=surname;
        persons.put(id,newUser);
        System.out.println("PATCH: id = " + id + "; Name = " + name
                + "; Surname = " + surname);
        return persons;
    }

    /* example request: curl -i --request PATCH localhost:8080 --data "id=0&surname=Ivanov" */
    @RequestMapping(method = RequestMethod.PATCH)
    Map userPATCH(String id,
                  @RequestParam(value = "surname",required = false,
                        defaultValue = "null")String surname,
                  @RequestParam(value = "name",required = false,
                        defaultValue = "null")String name) {
        if(!name.equals("null")) {
            persons.get(id).name = name;
            System.out.println("PATCH: id = " + id + "; name = " + name);
        }
        if(!surname.equals("null")){
            persons.get(id).surname = surname;
            System.out.println("PATCH: id = " + id + "; Surname = " + surname);
        }
        return persons;
    }

    /* example request: curl -i --request DELETE localhost:8080?id=0 */
    @RequestMapping(method = RequestMethod.DELETE)
    Map userDELETE(String id){
        System.out.println("DELETE: id = " + id + ";");
        persons.remove(id);
        return persons;
    }

}
