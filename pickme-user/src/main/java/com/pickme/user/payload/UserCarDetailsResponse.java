package com.pickme.user.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCarDetailsResponse {

    private long carId;
    private String registerNumber;
    private String modelName;
    private String color;
    private long seatLimit;

}
