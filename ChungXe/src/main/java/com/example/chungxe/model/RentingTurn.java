package com.example.chungxe.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RentingTurn {
    private String carName, licensePlates, startDate, endDate;
    private float totalPrice;
}
