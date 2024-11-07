package com.pickme.rides.services.implementation;

import com.pickme.rides.entity.Rides;
import com.pickme.rides.payloads.RegisterRideRequest;
import com.pickme.rides.payloads.RideDetailsResponse;
import com.pickme.rides.services.RidesServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService implements RidesServices {
    @Override
    public RideDetailsResponse registerRide(RegisterRideRequest request) {
        return null;
    }

    @Override
    public RideDetailsResponse updateRideInfo() {
        return null;
    }

    @Override
    public void deleteRide(String rideId) {

    }

    @Override
    public void cancelRide(String rideId, String userId) {

    }

    @Override
    public List<RideDetailsResponse> getRideByLocation(String startingPoint, String endingPoint) {
        return List.of();
    }

    @Override
    public List<RideDetailsResponse> getRideByRegisterUserId(String registerUserId) {
        return List.of();
    }

    private Rides toRides(RegisterRideRequest request) {
        return Rides.builder()

                .build();
    }

}
