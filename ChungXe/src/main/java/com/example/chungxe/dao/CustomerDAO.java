package com.example.chungxe.dao;

import com.example.chungxe.model.Customer;
import com.example.chungxe.model.RentingTurn;

import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<Customer> getCustomerList();
    Customer getCustomerByName(String name);
    ArrayList<RentingTurn> getRentingTurnByCustomerName(String fullName);
}
