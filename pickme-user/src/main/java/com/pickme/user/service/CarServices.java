package com.pickme.user.service;

import com.pickme.user.payload.CarRegisterRequest;
import com.pickme.user.payload.CarUpdateRequest;
import com.pickme.user.payload.UserCarDetailsResponse;

import java.util.List;

public interface CarServices {

    UserCarDetailsResponse registerCar(CarRegisterRequest request);

    List<UserCarDetailsResponse> getUserCars(String userId);

    UserCarDetailsResponse getCarInfo(String registrationNumber);

    void deleteCar(String userId, String registrationNumber);

    UserCarDetailsResponse updateCarDetails(CarUpdateRequest updateRequest);

}
