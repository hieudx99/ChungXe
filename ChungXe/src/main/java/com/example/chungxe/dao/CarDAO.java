package com.example.chungxe.dao;

import com.example.chungxe.model.RevenueStatistic;


import java.util.ArrayList;


public interface CarDAO {
    ArrayList<RevenueStatistic> getStatisticByCar(String startDateFilter, String endDateFilter);
}
