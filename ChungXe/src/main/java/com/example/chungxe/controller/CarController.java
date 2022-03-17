package com.example.chungxe.controller;

import com.example.chungxe.dao.CarDAO;
import com.example.chungxe.model.Car;
import com.example.chungxe.model.Statistic;
import com.example.chungxe.model.dto.DateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarDAO carDAO;

    @GetMapping("/statistic")
    public List<Statistic> getStatisticByCar(@RequestParam String startDate, String endDate){
        return carDAO.getStatisticByCar(startDate, endDate);
    }

    @GetMapping("")
    public List<Car> getListCar() {
        return carDAO.getListCar();
    }

    @GetMapping("/carid")
    public Car getCarByID(@RequestParam int carid) {
        return carDAO.getCarByID(carid);
    }

}
