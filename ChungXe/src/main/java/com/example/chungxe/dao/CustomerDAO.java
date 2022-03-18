package com.example.chungxe.dao;

import com.example.chungxe.model.Customer;

public interface CustomerDAO {
    public Customer checkLogin(String username, String password);
    public Customer getCustomerByID(int cusId);
}
