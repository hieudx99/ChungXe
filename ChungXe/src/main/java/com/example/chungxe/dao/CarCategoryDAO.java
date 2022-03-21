package com.example.chungxe.dao;

import com.example.chungxe.model.CarCategory;

import java.util.List;

public interface CarCategoryDAO {
    public CarCategory getCarCategoryByID(int carCategoryID);
    public List<CarCategory> getListCategory();
}
