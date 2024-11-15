package com.pickme.user.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarUpdateRequest {
    private long carId;
    private String registerNumber;
    private String modelName;
    private String color;
    private long seatLimit;

}
