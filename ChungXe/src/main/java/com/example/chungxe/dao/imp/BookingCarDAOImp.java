package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.BookingCarDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.BookingCar;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class BookingCarDAOImp extends DAO implements BookingCarDAO {
    @SneakyThrows
    @Override
    public ArrayList<BookingCar> getBookingCarList() {
        ArrayList<BookingCar> bookingCarList = new ArrayList<>();
        String sql = "SELECT booking.id as bookingId ,fullName as customerName, name as carName, licensePlates, totalPrice, startDate, endDate FROM \n" +
                "((booking inner join car on booking.carId = car.id) \n" +
                "inner join customer on booking.customerId = customer.id)\n";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int bookingId = rs.getInt("bookingId");
            String customerName = rs.getString("customerName");
            String carName = rs.getString("carName");
            String startDate = rs.getString("startDate");
            String endDate = rs.getString("endDate");
            float totalPrice = rs.getFloat("totalPRice");
            String licensePlates = rs.getString("licensePlates");

            bookingCarList.add(new BookingCar(bookingId, startDate, endDate, carName, customerName, licensePlates, totalPrice));
        }

        return bookingCarList;
    }

    @SneakyThrows
    @Override
    public ArrayList<BookingCar> getBookingCarListByDate(String startDateFilter, String endDateFilter) {
        ArrayList<BookingCar> bookingCarList = new ArrayList<>();
        String sql = "SELECT booking.id as bookingId ,fullName as customerName, name as carName, licensePlates, totalPrice, startDate, endDate FROM \n" +
                "((booking inner join car on booking.carId = car.id) \n" +
                "inner join customer on booking.customerId = customer.id)\n" +
                "where startDate between ? and ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, startDateFilter);
        ps.setString(2, endDateFilter);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int bookingId = rs.getInt("bookingId");
            String customerName = rs.getString("customerName");
            String carName = rs.getString("carName");
            String startDate = rs.getString("startDate");
            String endDate = rs.getString("endDate");
            float totalPrice = rs.getFloat("totalPRice");
            String licensePlates = rs.getString("licensePlates");

            bookingCarList.add(new BookingCar(bookingId, startDate, endDate, carName, customerName, licensePlates, totalPrice));
        }

        return bookingCarList;
    }
}
