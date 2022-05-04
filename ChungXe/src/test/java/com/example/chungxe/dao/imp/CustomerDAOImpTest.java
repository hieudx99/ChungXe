package com.example.chungxe.dao.imp;

import com.example.chungxe.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerDAOImpTest {

    @Autowired
    CustomerDAOImp customerDAOImp;


    @Test
    void checkLogin() {
        String username = "longnb";
        String password = "123456";
        Customer customer = customerDAOImp.checkLogin(username, password);
        assertNotNull(customer);
        assertEquals(customer.getId(), 2);
        assertEquals(customer.getFullName(), "Nguyen Bao Long");
        assertEquals(customer.getIdentityCard(), "0380456456456");
        assertEquals(customer.getTelephone(), "0972903455");
        assertEquals(customer.getAddress(), "Cau Giay");
        assertEquals(customer.getUsername(), "longnb");
    }
    @Test
    void checkLoginInvalidPassword() {
        String username = "longnb";
        String password = "1234567";
        Customer customer = customerDAOImp.checkLogin(username, password);
        assertNull(customer.getUsername());
        assertNull(customer.getIdentityCard());
        assertNull(customer.getAddress());
        assertNull(customer.getFullName());
        assertNull(customer.getTelephone());
    }

    @Test
    void checkLoginInvalidUsername() {
        String username = "longnb1";
        String password = "123456";
        Customer customer = customerDAOImp.checkLogin(username, password);
        assertNull(customer.getUsername());
        assertNull(customer.getIdentityCard());
        assertNull(customer.getAddress());
        assertNull(customer.getFullName());
        assertNull(customer.getTelephone());
    }
    @Test
    void checkLoginEmptyUsernamePassword() {
        String username = "";
        String password = "";
        Customer customer = customerDAOImp.checkLogin(username, password);
        assertNull(customer.getUsername());
        assertNull(customer.getIdentityCard());
        assertNull(customer.getAddress());
        assertNull(customer.getFullName());
        assertNull(customer.getTelephone());
    }




    @Test
    void getCustomerByID() {
        int id = 1;
        Customer customer = customerDAOImp.getCustomerByID(id);
        assertNotNull(customer);
        //
        assertEquals(customer.getId(), 1);
        assertEquals(customer.getFullName(), "Do Xuan Hieu");
        assertEquals(customer.getIdentityCard(), "038012312313");
        assertEquals(customer.getTelephone(), "0976199402");
        assertEquals(customer.getAddress(), "Hoang Mai");
        assertEquals(customer.getUsername(), "hieudx1");

    }


}