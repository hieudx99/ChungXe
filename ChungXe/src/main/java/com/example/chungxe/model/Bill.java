package com.example.chungxe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Bill {
    private int id;
    private String createAt;
    private String paymentStatus;
    private String confirmStatus;
    private String paymentMethod;
    private float totalPrice;
    private String startDate;
    private String endDate;
    private Employee employee;
    private Car car;
    private Customer customer;
}
