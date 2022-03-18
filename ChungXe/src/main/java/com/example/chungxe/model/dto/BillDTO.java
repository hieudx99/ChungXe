package com.example.chungxe.model.dto;

import com.example.chungxe.model.Car;
import com.example.chungxe.model.Customer;
import com.example.chungxe.model.Employee;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BillDTO {
    private int id;
    private String createAt;
    private String paymentStatus;
    private String confirmStatus;
    private String paymentMethod;
    private float totalPrice;
    private String startDate;
    private String endDate;
    private int employeeId;
    private int carId;
    private int customerId;
}