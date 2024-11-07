package com.pickme.user.service.implementation;

import com.pickme.user.entity.UserCarDetails;
import com.pickme.user.exception.CarException;
import com.pickme.user.payload.CarRegisterRequest;
import com.pickme.user.payload.UserCarDetailsResponse;
import com.pickme.user.repository.CarDetailsRepository;
import com.pickme.user.service.UserCarServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserCarService implements UserCarServices {

    private final CarDetailsRepository carDetailsRepository;

    @Override
    public UserCarDetailsResponse saveCar(CarRegisterRequest request) {

        if(carExistByRegistrationNumber(request.getRegisterNumber())) {
            throw new CarException("Car is already register with us", HttpStatus.BAD_REQUEST);
        }

        UserCarDetails carDetails = toUserCarDetails(request);

        UserCarDetails savedCar = carDetailsRepository.save(carDetails);

        return toCarResponse(savedCar);
    }


    @Override
    public List<UserCarDetailsResponse> getCarInfoByUserId(String userid) {
        return List.of();
    }

    @Override
    public void deleteCar(long carId) {

    }

    @Override
    public UserCarDetailsResponse updateCarInfo() {
        return null;
    }

    private UserCarDetails toUserCarDetails(CarRegisterRequest request) {
        return UserCarDetails.builder()
                .registerNumber(request.getRegisterNumber())
                .modelName(request.getModelName())
                .color(request.getColor())
                .seatLimit(request.getSeatLimit())
                .build();
    }

    private UserCarDetailsResponse toCarResponse(UserCarDetails savedCar) {
        return UserCarDetailsResponse.builder()
                .carId(savedCar.getCarId())
                .color(updateCarInfo().getColor())
                .seatLimit(savedCar.getSeatLimit())
                .modelName(savedCar.getModelName())
                .registerNumber(savedCar.getRegisterNumber())
                .build();
    }

    private boolean carExistByRegistrationNumber(String registrationNumber) {
        return carDetailsRepository.findByRegisterNumber(registrationNumber).isPresent();
    }

}
