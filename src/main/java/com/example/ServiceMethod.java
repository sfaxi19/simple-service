package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sfaxi19 on 29.11.2015.
 */
@RestController
public class ServiceMethod {

    private AtomicLong id = new AtomicLong(0);
    private Map<String, User> persons = new ConcurrentHashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Object getUser(@RequestParam(value = "id", required = false) String id) {
        if ((id == null) || !(persons.containsKey(id))) {
            return new SendMessage("Not correct id");
        }
        return persons.get(id).getUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean createUser(@RequestParam(value = "name",
                           required = false) String name,
                   @RequestParam(value = "surname",
                           required = false) String surname) {
        User newUser;
        newUser = new User(name, surname);
        persons.put(Long.toString(id.getAndIncrement()), newUser);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object updateUser(String id,
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

    @RequestMapping(method = RequestMethod.PATCH)
    public Object updateUser2(String id,
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

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean deleteUser(String id) {
        if ((id==null) || !(persons.containsKey(id))) {
            return false;
        }
        persons.remove(id);
        System.out.println("delete: id = " + id);
        return true;
    }
}
