package com.example.chungxe.dao;

import com.example.chungxe.model.Statistic;

import java.util.List;

public interface CarDAO {
    public List<Statistic> getStatisticByCar(String startDate, String endDate);

}
