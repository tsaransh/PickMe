package com.pickme.rides.payloads;

import java.util.Date;

public record RegisterRideRequest(
        String registerByUserId,
        String startingPoint,
        String endingPoint,
        Date rideDate,
        double amount
) {
}
