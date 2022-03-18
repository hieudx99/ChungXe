package com.example.chungxe.dao;

import com.example.chungxe.model.Bill;
import com.example.chungxe.model.dto.BillDTO;
import com.example.chungxe.model.dto.ShortBill;

import java.util.List;

public interface BillDAO {
    public List<ShortBill> getBillsByCar(int carId, String startDate, String endDate);
    public Bill getBillById(int billId);
    public String createBill(BillDTO billDTO);
}
