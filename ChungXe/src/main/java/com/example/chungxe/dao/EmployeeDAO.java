package com.example.chungxe.dao;

import com.example.chungxe.model.Employee;

public interface EmployeeDAO {

    Employee getEmployeeByID(int empId);
    Employee checkLogin(String username, String password);
}
