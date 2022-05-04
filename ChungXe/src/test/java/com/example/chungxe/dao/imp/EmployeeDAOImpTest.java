package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.EmployeeDAO;
import com.example.chungxe.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDAOImpTest {

    @Autowired
    EmployeeDAO employeeDAO;

//    @BeforeEach
//    void setUp() {
//        employeeDAO = new EmployeeDAOImp();
//    }

    @Test
    void getEmployeeByID() {
        int id = 1;
        Employee employee = employeeDAO.getEmployeeByID(id);
        assertNotNull(employee);
        assertEquals(employee.getTelephone(), "0946836148");
        assertEquals(employee.getId() , 1);
        assertEquals(employee.getFullName() , "Vu Duc Duy");
        assertEquals(employee.getUsername() , "duyvd");
        assertEquals(employee.getPosition() , "admin");
        assertEquals(employee.getSalary() , 10000);
        assertEquals(employee.getAddress() , "Hoang Mai");
    }


    @Test
    void checkLogin() {
        String username = "duyvd";
        String password = "123456";
        Employee employee = employeeDAO.checkLogin(username, password);
        assertNotNull(employee);
        assertEquals(employee.getTelephone(), "0946836148");
        assertEquals(employee.getId() , 1);
        assertEquals(employee.getFullName() , "Vu Duc Duy");
        assertEquals(employee.getUsername() , "duyvd");
        assertEquals(employee.getPosition() , "admin");
        assertEquals(employee.getSalary() , 10000);
        assertEquals(employee.getAddress() , "Hoang Mai");
    }

    @Test
    void checkLoginInvalidPassword() {
        String username = "duyvd";
        String password = "1234567";
        Employee employee = employeeDAO.checkLogin(username, password);
        assertNull(employee.getUsername());
        assertNull(employee.getAddress());
        assertNull(employee.getFullName());
        assertNull(employee.getTelephone());
    }

    @Test
    void checkLoginInvalidUsername() {
        String username = "duyvd1";
        String password = "123456";
        Employee employee = employeeDAO.checkLogin(username, password);
        assertNull(employee.getUsername());
        assertNull(employee.getAddress());
        assertNull(employee.getFullName());
        assertNull(employee.getTelephone());
    }
    @Test
    void checkLoginEmptyUsernamePassword() {
        String username = "";
        String password = "";
        Employee employee = employeeDAO.checkLogin(username, password);
        assertNull(employee.getUsername());
        assertNull(employee.getAddress());
        assertNull(employee.getFullName());
        assertNull(employee.getTelephone());
    }

}