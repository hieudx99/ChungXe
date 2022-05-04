package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.BranchDAO;
import com.example.chungxe.dao.CarCategoryDAO;
import com.example.chungxe.dao.CarDAO;
import com.example.chungxe.model.Car;
import com.example.chungxe.model.Statistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CarDAOImpTest {

    @Autowired
    CarDAO carDAOImp;

    private void setup() {
//        carDAOImp = new CarDAOImp();
//        BranchDAO branchDAO = new BranchDAOImp();
//        CarCategoryDAO categoryDAO = new CarCategoryImp();
//        carDAOImp.setCarCategoryDAO(categoryDAO);
//        carDAOImp.setBranchDAO(branchDAO);
    }

    @Test
    void searchCarDefault() {
        String kw = "";
        int nbrSeat = 0;
        int branchId = 0;
        int categoryId = 0;
        List<Car> listCar = carDAOImp.searchCar(kw, nbrSeat, branchId, categoryId);
        assertEquals(listCar.size(), 5);
    }

    @Test
    void searchCarByNameOnly() {
        String kw = "lux";
        int nbrSeat = 0;
        int branchId = 0;
        int categoryId = 0;
        List<Car> listCar = carDAOImp.searchCar(kw, nbrSeat, branchId, categoryId);
        assertEquals(listCar.size(), 1);
        assertEquals(listCar.get(0).getName(), "VINFAST LUX  A2.0");
    }

    @Test
    void searchCarByNbrSeatOnly() {
        String kw = "";
        int nbrSeat = 4;
        int branchId = 0;
        int categoryId = 0;
        List<Car> listCar = carDAOImp.searchCar(kw, nbrSeat, branchId, categoryId);
        assertEquals(listCar.size(), 2);
        assertEquals(listCar.get(0).getSeatNumber(), 4);
        assertEquals(listCar.get(1).getSeatNumber(), 4);
    }

    @Test
    void searchCarByBranchIdOnly() {
        String kw = "";
        int nbrSeat = 0;
        int branchId = 4;
        int categoryId = 0;
        List<Car> listCar = carDAOImp.searchCar(kw, nbrSeat, branchId, categoryId);
        assertEquals(listCar.size(), 1);
        assertEquals(listCar.get(0).getBranch().getId(),4);
    }

    @Test
    void searchCarByCategoryId() {
        String kw = "";
        int nbrSeat = 0;
        int branchId = 0;
        int categoryId = 4;
        List<Car> listCar = carDAOImp.searchCar(kw, nbrSeat, branchId, categoryId);
        assertEquals(listCar.size(), 2);
        assertEquals(listCar.get(0).getCarCategory().getId(),4);
        assertEquals(listCar.get(1).getCarCategory().getId(),4);
    }

    @Test
    void getListCar() {
        List<Car> listCar = carDAOImp.getListCar();
        assertEquals(listCar.size(), 5);
    }

    @Test
    void getCarByID() {
        int id = 1;
        Car car = carDAOImp.getCarByID(id);
        assertNotNull(car);
        assertEquals(car.getId(), 1);
        assertEquals(car.getName(), "VINFAST LUX  A2.0");
        assertEquals(car.getSeatNumber(), 4);
        assertEquals(car.getCarCategory().getId(), 3);
        assertEquals(car.getPrice(), 600000);

    }

    @Test
    void getListNbrSeat() {
        List<Integer> listNbrSeat = new ArrayList<>();
        listNbrSeat.add(4);
        listNbrSeat.add(5);
        listNbrSeat.add(7);
        List<Integer> listNbrSeatRes = carDAOImp.getListNbrSeat();
        assertEquals(listNbrSeatRes, listNbrSeat);

    }

    @Test
    void getStatisticByCar() {
        List<Statistic> listStatistic = new ArrayList<>();
        String startDate = "2022-03-01";
        String endDate = "2022-04-30";
        listStatistic = carDAOImp.getStatisticByCar(startDate, endDate);
        assertNotNull(listStatistic);
        assertEquals(listStatistic.get(0).getCarId(), 1);
        assertEquals(listStatistic.get(0).getDoanhthu(), 2200000);
        assertEquals(listStatistic.get(0).getCarName(), "VINFAST LUX  A2.0");
    }
}