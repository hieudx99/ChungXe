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
    private String licensePlates;
    private float price;
    private String image;
}
