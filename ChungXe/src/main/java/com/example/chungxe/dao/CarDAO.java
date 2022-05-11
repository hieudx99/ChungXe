package com.example.chungxe.dao;

import com.example.chungxe.model.Car;
import com.example.chungxe.model.Statistic;

import java.util.List;

public interface CarDAO {
    List<Car> getListCar();
    Car getCarByID(int carID);
    List<Car> searchCar(String kw, int nbrSeat, int branchId, int categoryId);
    List<Integer> getListNbrSeat();
    List<Statistic> getStatisticByCar(String startDate, String endDate);
}
