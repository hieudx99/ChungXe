package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.CarDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.RevenueStatistic;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class CarDAOImp extends DAO implements CarDAO {
    @SneakyThrows
    @Override
    public ArrayList<RevenueStatistic> getStatisticByCar(String startDateFilter, String endDateFilter) {
        ArrayList<RevenueStatistic> result = new ArrayList<>();
        String sql = "select car.id as carId, name as carName, licensePlates, sum(totalPrice) as revenue from\n" +
                "(booking inner join car on booking.carId = car.id)\n" +
                "where endDate between ? and ?\n" +
                "group by carId\n";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, startDateFilter);
        ps.setString(2, endDateFilter);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int carId = rs.getInt("carId");
            String carName = rs.getString("carName");
            String licensePlates = rs.getString("licensePlates");
            float revenue = rs.getFloat("revenue");

            result.add(new RevenueStatistic(
                    carId,
                    revenue,
                    carName,
                    licensePlates
            ));
        }

        return result;
    }
}
