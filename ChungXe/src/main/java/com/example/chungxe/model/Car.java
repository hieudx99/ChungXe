package com.example.chungxe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Car {
    private int id;
    private String name;
    private String color;
    private String licensePlate;
    private int seatNumber;
    private float price;
    private String image;
    private String status;
    private CarCategory carCategory;
    private Branch branch;
}
