package com.example.chungxe.dao;

import com.example.chungxe.model.CarCategory;

import java.util.List;

public interface CarCategoryDAO {
    CarCategory getCarCategoryByID(int carCategoryID);
    List<CarCategory> getListCategory();
}
