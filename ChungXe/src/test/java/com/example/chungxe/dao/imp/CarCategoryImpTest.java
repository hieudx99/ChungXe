package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CarCategoryDAO;
import com.example.chungxe.model.Car;
import com.example.chungxe.model.CarCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    void givenNotExistCarCategoryId_whenGetCarCategoryById_thenThrowException() {
        int id = 6;
        CarCategory carCategory = null;
        carCategory = carCategoryDAO.getCarCategoryByID(id);
        Exception exception = assertThrows(SQLException.class, () -> {
            throw new SQLException("Category not exist!");
        });
        assertEquals(exception.getMessage(), "Category not exist!");
    }

    @Test
    void getListCategory() {
        List<CarCategory> listCarCategory = null;
        listCarCategory = carCategoryDAO.getListCategory();
        assertNotNull(listCarCategory);
        assertEquals(listCarCategory.get(0).getName(), "A");

    }
}