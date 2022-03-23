package com.example.chungxe.controller;

import com.example.chungxe.dao.EmployeeDAO;
import com.example.chungxe.model.Customer;
import com.example.chungxe.model.Employee;
import com.example.chungxe.model.dto.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class EmployeeController {

    @Autowired
    EmployeeDAO employeeDAO;

    @PostMapping("/login")
    public Employee checkLogin(@RequestBody Credential credential) {
        return employeeDAO.checkLogin(credential.getUsername(), credential.getPassword());
    }
}
