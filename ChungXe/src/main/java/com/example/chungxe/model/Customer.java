package com.example.chungxe.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    private int id;
    private String fullName;
    private String identityCard;
    private String telephone;
    private String address;
    private String username;
    private String password;
}
