package com.example.chungxe.dao.imp;


import com.example.chungxe.dao.BillDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Bill;
import com.example.chungxe.model.dto.ShortBill;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillDAOImp extends DAO implements BillDAO {

    public BillDAOImp(){
        super();
    }

    @Override
    public List<ShortBill> getBillsByCar(int carId, String startDate, String endDate) {
        List<ShortBill> result = new ArrayList<>();
        String sql = "select id, carId, createdAt  from tblbill\n" +
                "where carId = ? and createdAt between ? and ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String createdAt = rs.getString("createdAt");
                result.add(new ShortBill(id, carId, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Bill getBillById(int billId) {
        Bill result = null;
        String sql = "select * from tblBill where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String createdAt = rs.getString("createdAt");
                String paymentStatus = rs.getString("paymentStatus");
                String confirmStatus = rs.getString("confirmStatus");
                String paymentMethod = rs.getString("paymentMethod");
                float totalPrice = rs.getFloat("totalPrice");
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                int employeeId = rs.getInt("employeeId");
                int customerId = rs.getInt("customerId");
                int carId = rs.getInt("carId");
                result =  new Bill(
                        billId,
                        createdAt,
                        paymentStatus,
                        confirmStatus,
                        paymentMethod,
                        totalPrice,
                        startDate,
                        endDate,
                        employeeId,
                        carId,
                        customerId
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

}
