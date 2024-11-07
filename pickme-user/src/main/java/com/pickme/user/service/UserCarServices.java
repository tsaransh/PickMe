package com.pickme.user.service;

import com.pickme.user.payload.CarRegisterRequest;
import com.pickme.user.payload.UserCarDetailsResponse;

import java.util.List;

public interface UserCarServices {

    UserCarDetailsResponse saveCar(CarRegisterRequest request);
    List<UserCarDetailsResponse> getCarInfoByUserId(String userid);
    void deleteCar(long carId);
    UserCarDetailsResponse updateCarInfo();

}
