package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sfaxi19 on 29.11.2015.
 */
@RestController
public class ServiceMethod {

    private final Map<String, User> persons = new HashMap<>();
    private int id = 0;

    @RequestMapping(method = RequestMethod.GET)
    Object getUser(@RequestParam(value = "id", required = false) String id) {
        if ((id == null) || !(persons.containsKey(id))) {
            return new SendMessage("Not correct id");
        }
        System.out.println("get: id = " + id
                + "; Name = " + persons.get(id).getUser().name
                + "; Surname = " + persons.get(id).getUser().surname);
        return persons.get(id).getUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    int createUser(@RequestParam(value = "name",
                           required = false) String name,
                   @RequestParam(value = "surname",
                           required = false) String surname) {
        User newUser;
        newUser = new User(name, surname);
        synchronized (persons) {
            persons.put(Integer.toString(id), newUser);
            System.out.println("create: id = " + id + "; Name = " + name
                    + "; Surname = " + surname);
            id++;
        }
        return 0;
    }

    @RequestMapping(method = RequestMethod.PUT)
    Object updateUser(String id,
                      @RequestParam(value = "name", required = false,
                              defaultValue = "null") String name,
                      @RequestParam(value = "surname", required = false,
                              defaultValue = "null") String surname) {
        if ((id == null) || !(persons.containsKey(id))) {
            return new SendMessage("Not correct id");
        }
        persons.get(id).setName(name);
        persons.get(id).setSurname(surname);
        System.out.println("update: id = " + id + "; Name = " + name
                + "; Surname = " + surname);
        return persons;
    }

    /* example request:
        curl --request PATCH localhost:8080 --data "id=0&surname=Ivanov" */
    @RequestMapping(method = RequestMethod.PATCH)
    Object updateUser2(String id,
                       @RequestParam(value = "surname",
                               required = false) String surname,
                       @RequestParam(value = "name",
                               required = false) String name) {
        if ((id == null) || !(persons.containsKey(id))) {
            return new SendMessage("Not correct id");
        }
        if (name != null) {
            persons.get(id).setName(name);
            System.out.println("path: id = " + id + "; name = " + name);
        }
        if (surname != null) {
            persons.get(id).setSurname(surname);
            System.out.println("path: id = " + id + "; surname = " + surname);
        }
        return persons;
    }

    /* example request: curl --request DELETE localhost:8080?id=0 */
    @RequestMapping(method = RequestMethod.DELETE)
    boolean deleteUser(String id) {
        if ((id==null) || !(persons.containsKey(id))) {
            return false;
        }
        persons.remove(id);
        System.out.println("delete: id = " + id);
        return true;
    }
}
