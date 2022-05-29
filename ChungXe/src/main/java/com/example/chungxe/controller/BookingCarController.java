package com.example.chungxe.controller;


import com.example.chungxe.dao.BookingCarDAO;
import com.example.chungxe.model.BookingCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/booking-car")
public class BookingCarController {

    @Autowired
    BookingCarDAO bookingCarDAO;

    @GetMapping("/list")
    public ArrayList<BookingCar> getCustomerList(){
        return bookingCarDAO.getBookingCarList();
    }

    @GetMapping("/list-by-date")
    public ArrayList<BookingCar> getCustomerList(@RequestParam String startDate, String endDate){
        System.out.println(startDate + " " +  endDate);
        return bookingCarDAO.getBookingCarListByDate(startDate, endDate);
    }
}
