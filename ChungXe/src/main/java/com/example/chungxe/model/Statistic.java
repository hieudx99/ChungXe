package com.example.chungxe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Statistic {
    private int doanhthu, carId;
    private String startDate, endDate, carName;
}
