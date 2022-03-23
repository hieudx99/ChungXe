package com.example.chungxe.dao.imp;


import com.example.chungxe.dao.*;
import com.example.chungxe.model.Bill;
import com.example.chungxe.model.Car;
import com.example.chungxe.model.Customer;
import com.example.chungxe.model.Employee;
import com.example.chungxe.model.dto.BillDTO;
import com.example.chungxe.model.dto.ShortBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillDAOImp extends DAO implements BillDAO {

    public BillDAOImp(){
        super();
    }

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CarDAO carDAO;

    @Override
    public List<ShortBill> getBillsByCar(int carId, String startDate, String endDate) {
        List<ShortBill> result = new ArrayList<>();
        String sql = "select tblbill.id as id, tblcar.name as carName, createdAt, carId from tblbill\n" +
                "inner join tblcar\n" +
                "on tblbill.carId = tblcar.id\n" +
                "where carId = ? and confirmStatus = \"Confirmed\" and createdAt BETWEEN ? and ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, carId);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String createdAt = rs.getString("createdAt");
                String carName = rs.getString("carName");
                result.add(new ShortBill(id, carId, carName,  createdAt));
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
                Employee employee = employeeDAO.getEmployeeByID(employeeId);
                Customer customer = customerDAO.getCustomerByID(customerId);
                Car car = carDAO.getCarByID(carId);
                result =  new Bill(billId, createdAt, paymentStatus, confirmStatus, paymentMethod, totalPrice,
                        startDate, endDate, employee, car, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public Bill createBill(BillDTO billDTO) {
        Bill bill = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String createdAt = dtf.format(now);

        String sql = "INSERT into tblBill(createdAt, paymentStatus, confirmStatus, paymentMethod, totalPrice, startDate, endDate, carId, customerId)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, createdAt);
            ps.setString(2, billDTO.getPaymentStatus());
            ps.setString(3, billDTO.getConfirmStatus());
            ps.setString(4, billDTO.getPaymentMethod());
            ps.setFloat(5, billDTO.getTotalPrice());
            ps.setString(6, billDTO.getStartDate());
            ps.setString(7, billDTO.getEndDate());
            ps.setInt(8, billDTO.getCarId());
            ps.setInt(9, billDTO.getCustomerId());
            int res = ps.executeUpdate();
            if (res > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    bill = getBillById(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getNotConfirmedBills()  {
        List<Bill> result = new ArrayList<>();
        String sql = "select * from tblbill\n" +
                "where confirmStatus=\"pending\"";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int billId = rs.getInt("id");
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
                Employee employee = employeeDAO.getEmployeeByID(employeeId);
                Customer customer = customerDAO.getCustomerByID(customerId);
                Car car = carDAO.getCarByID(carId);
                result.add(  new Bill(billId, createdAt, paymentStatus, confirmStatus, paymentMethod, totalPrice,
                        startDate, endDate, employee, car, customer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void confirmBill(int billId, String status) {
        String sql = "update tblbill\n" +
                "set confirmStatus=?\n" +
                "where id = ?;\n";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, billId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
