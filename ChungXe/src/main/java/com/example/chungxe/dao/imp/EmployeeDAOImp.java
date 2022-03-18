package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.DAO;
import com.example.chungxe.dao.EmployeeDAO;
import com.example.chungxe.model.Employee;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class EmployeeDAOImp extends DAO implements EmployeeDAO {

    @Override
    public Employee getEmployeeByID(int empId) {
        Employee employee = null;
        String sql = "SELECT * FROM tblEmployee WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                String telephone = rs.getString("telephone");
                String position = rs.getString("position");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                String username = rs.getString("username");
                employee = Employee.builder()
                        .id(empId)
                        .fullName(fullname)
                        .telephone(telephone)
                        .position(position)
                        .address(address)
                        .salary(salary)
                        .username(username)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}