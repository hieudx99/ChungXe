package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CustomerDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Customer;
import com.example.chungxe.model.RentingTurn;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CustomerDAOImp extends DAO implements CustomerDAO {

    public CustomerDAOImp() {
        super();
    }


    @SneakyThrows
    @Override
    public ArrayList<Customer> getCustomerList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String fullName = rs.getString("fullName");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String username = rs.getString("username");
            String password = rs.getString("password");
            customerList.add(new Customer(
                    id,
                    fullName,
                    phoneNumber,
                    address,
                    username,
                    password
            ));
        }

        System.out.println("customers: " + customerList.size());
        return customerList;
    }

    @Override
    public Customer getCustomerByName(String name) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE fullName = ?";
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String telephone = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String password = rs.getString("password");
                customer = Customer.builder()
                        .id(id)
                        .fullName(fullName)
                        .phoneNumber(telephone)
                        .address(address)
                        .username(username)
                        .password(password)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @SneakyThrows
    @Override
    public ArrayList<RentingTurn> getRentingTurnByCustomerName(String fullName) {
        ArrayList<RentingTurn> result = new ArrayList<>();
        String sql = "select name as carName, licensePlates, startDate, endDate, totalPrice from \n" +
                "((booking inner join customer on booking.customerId = customer.id)\n" +
                "inner join car on car.id = carId)\n" +
                "where fullName = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, fullName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            String carName = rs.getString("carName");
            String licensePlates = rs.getString("licensePlates");
            String startDate = rs.getString("startDate");
            String endDate = rs.getString("endDate");
            float totalPrice = rs.getFloat("totalPrice");

            result.add(new RentingTurn(
                    carName,
                    licensePlates,
                    startDate,
                    endDate,
                    totalPrice
            ));
        }

        return result;
    }
}
