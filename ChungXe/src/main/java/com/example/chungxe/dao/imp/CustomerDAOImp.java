package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CustomerDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Customer;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CustomerDAOImp extends DAO implements CustomerDAO {

    public CustomerDAOImp() {
        super();
    }

    @Override
    public Customer checkLogin(String username, String password) {
        Customer customer = null;
        String sql = "SELECT * FROM tblCustomer WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullname");
                String identityCard = rs.getString("identityCard");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                customer = Customer.builder()
                        .id(id)
                        .fullName(fullName)
                        .identityCard(identityCard)
                        .telephone(telephone)
                        .address(address)
                        .username(username)
                        .build();
            }
            else {
                customer = new Customer();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public Customer getCustomerByID(int cusId) {
        Customer customer = null;
        String sql = "SELECT * FROM tblCustomer WHERE id = ?";
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setInt(1, cusId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullname");
                String identityCard = rs.getString("identityCard");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String username = rs.getString("username");
                customer = Customer.builder()
                        .id(cusId)
                        .fullName(fullName)
                        .identityCard(identityCard)
                        .telephone(telephone)
                        .address(address)
                        .username(username)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
