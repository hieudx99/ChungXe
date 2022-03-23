package com.example.chungxe.dao;

import com.example.chungxe.model.Customer;

public interface CustomerDAO {
    Customer checkLogin(String username, String password);
    Customer getCustomerByID(int cusId);
}
