package com.example.chungxe.dao;

import com.example.chungxe.model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeDAO {

    Employee getEmployeeByID(int empId);
    ResponseEntity<Employee> checkLogin(String username, String password);
}
