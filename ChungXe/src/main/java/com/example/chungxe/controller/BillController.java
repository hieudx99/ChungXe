package com.example.chungxe.controller;

import com.example.chungxe.dao.BillDAO;
import com.example.chungxe.model.Bill;
import com.example.chungxe.model.dto.BillDTO;
import com.example.chungxe.model.dto.ShortBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    BillDAO billDAO;

    @GetMapping("car")
    public List<ShortBill> getBillsByCar(@RequestParam int carId, String startDate, String endDate){
        return billDAO.getBillsByCar(carId, startDate, endDate);
    }
    @GetMapping("bill_id")
    public Bill getBillById(@RequestParam int id){
        return billDAO.getBillById(id);
    }

    @PostMapping("/create_bill")
    public String createBill(@RequestBody BillDTO billDTO) {
        return billDAO.createBill(billDTO);
    }
}
