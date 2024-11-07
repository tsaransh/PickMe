package com.pickme.rides.services;

import com.pickme.rides.payloads.RegisterRideRequest;
import com.pickme.rides.payloads.RideDetailsResponse;

import java.util.List;

public interface RidesServices {

    public RideDetailsResponse registerRide(RegisterRideRequest request);

    public RideDetailsResponse updateRideInfo();

    public void deleteRide(String rideId);

    public void cancelRide(String rideId, String userId);

    public List<RideDetailsResponse> getRideByLocation(String startingPoint, String endingPoint);

    public List<RideDetailsResponse> getRideByRegisterUserId(String registerUserId);

}
