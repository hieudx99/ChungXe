package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.BranchDAO;
import com.example.chungxe.dao.CarCategoryDAO;
import com.example.chungxe.dao.CarDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Branch;
import com.example.chungxe.model.Car;
import com.example.chungxe.model.CarCategory;
import com.example.chungxe.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarDAOImp extends DAO implements CarDAO {

    public CarDAOImp(){
        super();
    }

    @Autowired
    private CarCategoryDAO carCategoryDAO;
    @Autowired
    private BranchDAO branchDAO;

    @Override
    public List<Car> getListCar() {
        List<Car> listCar = new ArrayList<>();
        String sql = "SELECT * FROM tblCar";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String color = rs.getString("color");
                String licensePlate = rs.getString("licensePlate");
                int seatNumber = rs.getInt("seatNumber");
                float price = rs.getFloat("price");
                String image = rs.getString("image");
                String status = rs.getString("status");
                int categoryId = rs.getInt("categoryId");
                int branchId = rs.getInt("branchId");
                CarCategory carCategory = carCategoryDAO.getCarCategoryByID(categoryId);
                Branch branch = branchDAO.getBranchByID(branchId);
                Car car = Car.builder()
                        .id(id)
                        .name(name)
                        .color(color)
                        .licensePlate(licensePlate)
                        .seatNumber(seatNumber)
                        .price(price)
                        .image(image)
                        .status(status)
                        .carCategory(carCategory)
                        .branch(branch)
                        .build();
                listCar.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCar;
    }

    @Override
    public Car getCarByID(int carID) {
        Car car = null;
        String sql = "SELECT * FROM tblCar WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, carID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String color = rs.getString("color");
                String licensePlate = rs.getString("licensePlate");
                int seatNumber = rs.getInt("seatNumber");
                float price = rs.getFloat("price");
                String image = rs.getString("image");
                String status = rs.getString("status");
                int categoryId = rs.getInt("categoryId");
                int branchId = rs.getInt("branchId");
                CarCategory carCategory = carCategoryDAO.getCarCategoryByID(categoryId);
                Branch branch = branchDAO.getBranchByID(branchId);
                car = Car.builder()
                        .id(carID)
                        .name(name)
                        .color(color)
                        .licensePlate(licensePlate)
                        .seatNumber(seatNumber)
                        .price(price)
                        .image(image)
                        .status(status)
                        .carCategory(carCategory)
                        .branch(branch)
                        .build();
            }
            else {
                throw new SQLException("Car not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public List<Car> searchCar(String kw, int nbrSeat, int branchId, int categoryId) {
        List<Car> listCar = new ArrayList<>();
        String sql = "SELECT * FROM tblCar c \n" +
                "WHERE 1=1 \n";
        if (kw != null && !kw.isEmpty()) {
            sql += "AND c.name like ?";
        }
        if (nbrSeat > 0) {
            sql += "AND c.seatNumber = " + nbrSeat + "\n";
        }
        if (branchId > 0) {
            sql += "AND c.branchId = " + branchId + "\n";
        }
        if (categoryId > 0) {
            sql += "AND c.categoryId = " + categoryId;
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (kw != null && !kw.isEmpty()) {
                ps.setString(1, "%" + kw + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String color = rs.getString("color");
                String licensePlate = rs.getString("licensePlate");
                int seatNumber = rs.getInt("seatNumber");
                float price = rs.getFloat("price");
                String image = rs.getString("image");
                String status = rs.getString("status");
                int branch_id = rs.getInt("branchId");
                int category_id = rs.getInt("categoryId");
                CarCategory carCategory = carCategoryDAO.getCarCategoryByID(category_id);
                Branch branch = branchDAO.getBranchByID(branch_id);
                Car car = Car.builder()
                        .id(id)
                        .name(name)
                        .color(color)
                        .licensePlate(licensePlate)
                        .seatNumber(seatNumber)
                        .price(price)
                        .image(image)
                        .status(status)
                        .carCategory(carCategory)
                        .branch(branch)
                        .build();
                listCar.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCar;
    }

    @Override
    public List<Integer> getListNbrSeat() {
        List<Integer> listNbrSeat = new ArrayList<>();
        String sql = "SELECT DISTINCT seatNumber from tblCar";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int nbr_of_seat = rs.getInt("seatNumber");
                listNbrSeat.add(nbr_of_seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNbrSeat;
    }

    @Override
    public List<Statistic> getStatisticByCar(String startDate, String endDate) {
        List<Statistic> result = new ArrayList<>();
        String sql = "select tblcar.id, shortBill.doanhthu, tblcar.name from tblcar\n" +
                "inner join (select sum(totalPrice) as doanhthu, carId, createdAt from tblbill\n" +
                "where  confirmStatus = 'Confirmed' AND createdAt BETWEEN ? AND ?\n" +
                "group by carId)as shortBill\n" +
                "on tblcar.id = carId\n" +
                "order by shortBill.doanhthu DESC";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int doanhthu = rs.getInt("doanhthu");
                int carId = rs.getInt("id");
                String carName = rs.getString("name");
                result.add(new Statistic(doanhthu, carId,  startDate, endDate, carName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
