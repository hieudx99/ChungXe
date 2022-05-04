package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CarCategoryDAO;
import com.example.chungxe.model.Car;
import com.example.chungxe.model.CarCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarCategoryImpTest {

    @Autowired
    CarCategoryDAO carCategoryDAO;

//    @BeforeEach
//    void setUp() {
//        carCategoryDAO = new CarCategoryImp();
//    }

    @Test
    void getCarCategoryByID() {
        int id = 1;
        CarCategory carCategory = null;
        carCategory = carCategoryDAO.getCarCategoryByID(id);
        assertNotNull(carCategory);
        assertEquals(carCategory.getName(), "A");
    }

    @Test
    void getListCategory() {
        List<CarCategory> listCarCategory = null;
        listCarCategory = carCategoryDAO.getListCategory();
        assertNotNull(listCarCategory);
        assertEquals(listCarCategory.get(0).getName(), "A");

    }
}