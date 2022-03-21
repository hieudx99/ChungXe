package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CarCategoryDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.CarCategory;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CarCategoryImp extends DAO implements CarCategoryDAO {

    public CarCategoryImp() {
        super();
    }

    @Override
    public CarCategory getCarCategoryByID(int carCategoryID) {
        CarCategory carCategory = null;
        String sql = "SELECT * FROM tblCarCategory WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, carCategoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                carCategory = new CarCategory(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carCategory;
    }

    @Override
    public List<CarCategory> getListCategory() {
        List<CarCategory> listCategory = new ArrayList<>();
        String sql = "SELECT * FROM tblCarCategory";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                CarCategory carCategory = CarCategory.builder()
                        .id(id)
                        .name(name)
                        .build();
                listCategory.add(carCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCategory;
    }
}
