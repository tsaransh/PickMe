package com.pickme.user.payload;

import lombok.Data;

@Data
public class CarRegisterRequest {
    private String registerNumber;
    private String modelName;
    private String color;
    private long seatLimit;
}
