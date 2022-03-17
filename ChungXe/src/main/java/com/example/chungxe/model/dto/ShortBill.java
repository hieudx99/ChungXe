package com.example.chungxe.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ShortBill {
    private int id, carId;
    private String createAt;
}
