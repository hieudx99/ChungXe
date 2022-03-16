package com.example.chungxe.dao.imp;


import com.example.chungxe.dao.BillDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDAOImp extends DAO implements BillDAO {

    public BillDAOImp(){
        super();
    }

    @Override
    public List<Bill> getBillsByCar(int carId, String startDate, String endDate) {
        return null;
    }
}
