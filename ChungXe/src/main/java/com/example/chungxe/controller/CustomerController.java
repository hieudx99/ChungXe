package com.example.chungxe.controller;


import com.example.chungxe.dao.CustomerDAO;
import com.example.chungxe.model.Customer;
import com.example.chungxe.model.RentingTurn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerDAO customerDAO;

//    @PostMapping("/login")
//    public Customer checkLogin(@RequestBody Credential credential) {
//        return customerDAO.checkLogin(credential.getUsername(), credential.getPassword());
//    }

    @GetMapping("/customer-list")
    public ArrayList<Customer> getCustomerList(){
        return customerDAO.getCustomerList();
    }

    @GetMapping("/customer-detail")
    public Customer getCustomerByName(@RequestParam String customer_name){
        return customerDAO.getCustomerByName(customer_name);
    }

    @GetMapping("/renting-turns")
    public ArrayList<RentingTurn> getRentingTurnsByCustomerName(@RequestParam String customer_name){
        return customerDAO.getRentingTurnByCustomerName(customer_name);
    }
}
