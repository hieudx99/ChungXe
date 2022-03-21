package com.example.chungxe.dao;

import com.example.chungxe.model.Car;
import com.example.chungxe.model.Statistic;

import java.util.List;

public interface CarDAO {
    public List<Car> getListCar();
    public Car getCarByID(int carID);
    public List<Car> searchCar(String kw, int nbrSeat, int branchId, int categoryId);
    public List<Statistic> getStatisticByCar(String startDate, String endDate);

}
