package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CarDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Statistic;
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

    @Override
    public List<Statistic> getStatisticByCar(String startDate, String endDate) {
        List<Statistic> result = new ArrayList<>();
        String sql = "select tblcar.id, shortBill.doanhthu, tblcar.name \n" +
                "from tblcar\n" +
                "inner join (\n" +
                "\tselect sum(totalPrice) as doanhthu, carId, createdAt from tblbill\n" +
                "\twhere createdAt BETWEEN ? AND ?\n" +
                ") as shortBill\n" +
                "on tblcar.id = carId\n";

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
