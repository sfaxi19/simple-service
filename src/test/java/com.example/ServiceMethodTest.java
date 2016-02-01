package com.example;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by sfaxi19 on 30.01.16.
 */
public class ServiceMethodTest {

    private static ServiceMethod service;
    SendMessage fail_id = new SendMessage("Not correct id");

    @BeforeClass
    public static void before_class() {
        service = new ServiceMethod();
        for (int i = 0; i < 100; i++) {
            service.createUser("name_" + i, "surname_" + i);
        }
    }

    @Test
    public void test_getUser(){
        assertTrue(new OutUser("name_3","surname_3")
                .equals((OutUser)service.getUser("3")));
        assertTrue(fail_id.equals((SendMessage)service.getUser("-1")));
        assertTrue(fail_id.equals((SendMessage)service.getUser(null)));
    }

    @Test
    public void test_create() {
        assertEquals(service.createUser(null,null), 0);
    }

    @Test
    public void test_update(){
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser("-1","upd_1","upd_2")));
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser("1000","upd_1","upd_2")));
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser("a","upd_1","upd_2")));
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser(null,"upd_1","upd_2")));
        assertNotEquals(service.updateUser("1",null,null),null);
        assertNotEquals(service.updateUser("0","upd_1","upd_2"),null);
    }

    @Test
    public void test_update2(){
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser2("-1","upd_1","upd_2")));
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser2("1000","upd_1","upd_2")));
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser2("a","upd_1","upd_2")));
        assertTrue(fail_id.equals((SendMessage)service
                .updateUser2(null,"upd_1","upd_2")));
        assertNotEquals(service.updateUser2("1",null,null),null);
        assertNotEquals(service.updateUser2("0","upd_1","upd_2"),null);
    }

    @Test
    public void test_delete(){
        assertTrue(service.deleteUser("1"));
        assertFalse(service.deleteUser("-1"));
        assertFalse(service.deleteUser(null));
    }

}