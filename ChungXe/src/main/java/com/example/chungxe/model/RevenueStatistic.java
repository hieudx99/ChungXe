package com.example.chungxe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RevenueStatistic {
    private int carId;
    private float revenue;
    private String  carName, licensePlates;
}
