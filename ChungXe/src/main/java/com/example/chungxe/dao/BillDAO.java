package com.example.chungxe.dao;

import com.example.chungxe.model.Bill;
import com.example.chungxe.model.dto.BillDTO;
import com.example.chungxe.model.dto.ShortBill;

import java.util.List;

public interface BillDAO {
    List<ShortBill> getBillsByCar(int carId, String startDate, String endDate);
    Bill getBillById(int billId);

    List<Bill> getNotConfirmedBills();
    void confirmBill(int billId, String status);

    Bill createBill(BillDTO billDTO);

}
