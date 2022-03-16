package com.example.chungxe.dao;

import com.example.chungxe.model.Bill;

import java.util.List;

public interface BillDAO {
    public List<Bill> getBillsByCar(int carId, String startDate, String endDate);
}
