package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.EmployeeDAO;
import com.example.chungxe.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

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
    void givenInvalidEmployeeId_whenGetEmployeeByID_thenThrowException(){
        int id = 100000000;
        Employee employee = employeeDAO.getEmployeeByID(id);
        SQLException exception = assertThrows(SQLException.class, () -> {
            throw new SQLException("Invalid Employee ID");
        });

        assertEquals(exception.getMessage(), "Invalid Employee ID");
    }


    @Test
    void checkLogin() {
        String username = "duyvd";
        String password = "123456";
        ResponseEntity<Employee> response = employeeDAO.checkLogin(username, password);
        Employee employee = response.getBody();
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
    void givenInvalidPassword_whenCheckLogin_thenLoginFail() {
        String username = "duyvd";
        String password = "1234567";
        ResponseEntity<Employee> response = employeeDAO.checkLogin(username, password);
        Employee employee = response.getBody();
        assertNull(employee);

    }

    @Test
    void givenInvalidUsername_whenCheckLogin_thenLoginFail() {
        String username = "duyvd1";
        String password = "123456";
        ResponseEntity<Employee> response = employeeDAO.checkLogin(username, password);
        Employee employee = response.getBody();
        assertNull(employee);
    }

    @Test
    void givenEmptyUsernameAndPassword_whenCheckLogin_thenLoginFail() {
        String username = "";
        String password = "";
        ResponseEntity<Employee> response = employeeDAO.checkLogin(username, password);
        Employee employee = response.getBody();
        assertEquals(response.getStatusCode().value(), 404);
        assertNull(employee);
    }

}