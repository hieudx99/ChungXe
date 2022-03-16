package com.example.chungxe.controller;

import com.example.chungxe.dao.BillDAO;
import com.example.chungxe.model.dto.ShortBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
