package com.example.chungxe.dao;

import com.example.chungxe.model.BookingCar;

import java.util.ArrayList;

public interface BookingCarDAO {
     ArrayList<BookingCar> getBookingCarList();
     ArrayList<BookingCar> getBookingCarListByDate(String startDateFilter, String endDateFilter);
}
