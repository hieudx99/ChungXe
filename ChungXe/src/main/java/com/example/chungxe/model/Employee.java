package com.example.chungxe.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Employee {
    private int id;
    private String fullName;
    private String telephone;
    private String position;
    private String address;
    private float salary;
    private String username;
    private String password;
}
