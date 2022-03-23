package com.example.chungxe.dao;

import com.example.chungxe.model.Employee;

public interface EmployeeDAO {

    public Employee getEmployeeByID(int empId);
    public Employee checkLogin(String username, String password);
}
