package com.example.chungxe.dao;

import com.example.chungxe.model.Car;
import com.example.chungxe.model.Statistic;

import java.util.List;

public interface CarDAO {
    public List<Car> getListCar();
    public Car getCarByID(int carID);
    public List<Statistic> getStatisticByCar(String startDate, String endDate);

}
