package com.example.chungxe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class BookingCar {

    private int bookingId;
    private String startDate, endDate, carName, customerName, licensePlates;
    private float totalPrice;
}
