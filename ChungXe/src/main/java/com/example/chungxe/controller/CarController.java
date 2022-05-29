package com.example.chungxe.controller;

import com.example.chungxe.dao.CarDAO;
import com.example.chungxe.model.RevenueStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarDAO carDao;

    @GetMapping("/revenue-statistic")
    public ArrayList<RevenueStatistic> getCustomerList(@RequestParam String startDate, String endDate){
        System.out.println(startDate + " " + endDate);
        return carDao.getStatisticByCar(startDate, endDate);
    }



}
