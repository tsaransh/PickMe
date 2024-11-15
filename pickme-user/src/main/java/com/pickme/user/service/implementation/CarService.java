package com.pickme.user.service.implementation;

import com.pickme.user.entity.UserCarDetails;
import com.pickme.user.entity.UserDetails;
import com.pickme.user.exception.CarException;
import com.pickme.user.payload.CarRegisterRequest;
import com.pickme.user.payload.CarUpdateRequest;
import com.pickme.user.payload.UserCarDetailsResponse;
import com.pickme.user.repository.CarDetailsRepository;
import com.pickme.user.service.CarServices;
import com.pickme.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService implements CarServices {

    private final CarDetailsRepository carDetailsRepository;
    private final UserService userService;

    @Override
    public UserCarDetailsResponse registerCar(CarRegisterRequest request) {

        if(carDetailsRepository.findByRegistrationNumber(request.getRegisterNumber()).isPresent()) {
            throw new CarException("Car is already added", HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = userService.getUserDetails(request.getCarOwnerUserID());

        UserCarDetails carDetails = toCarEntity(request, userDetails);

        carDetails.setCarActive(true);

        userDetails.getCarDetails().add(carDetails);

        userService.saveUserDetails(userDetails);

        return toCarResponse(userDetails.getCarDetails().getLast());
    }

    @Override
    public List<UserCarDetailsResponse> getUserCars(String userId) {

        List<UserCarDetails> carDetails = carDetailsRepository
                .findByUserDetailsUserIdAndIsCarActive(userId, true);

        if (carDetails.isEmpty()) {
            throw new CarException("No car found with id: " + userId, HttpStatus.BAD_REQUEST);
        }

        return carDetails.stream()
                .map(this::toCarResponse)
                .collect(Collectors.toList());
    }



    @Override
    public UserCarDetailsResponse getCarInfo(String registrationNumber) {

        UserCarDetails carDetails = getCarByRegistrationNumber(registrationNumber);

        if(carDetails.isCarActive())
            return toCarResponse(carDetails);
        else
            throw new CarException("No active car found with registration number: "+ registrationNumber, HttpStatus.BAD_REQUEST);

    }

    @Override
    public void deleteCar(String userId, String registrationNumber) {
        UserDetails currentUser = userService.getUserDetails(userId);
        UserCarDetails carDetails = getCarByRegistrationNumber(registrationNumber);

        if(!carDetails.isCarActive())
            throw new CarException("Car is already been removed", HttpStatus.BAD_REQUEST);


        if(currentUser.getUserId().compareTo(carDetails.getUserDetails().getUserId())== 0) {
            carDetails.setCarActive(false);
            carDetailsRepository.save(carDetails);
            return;
        }

        throw new CarException("Failed to delete car, please try again later", HttpStatus.BAD_REQUEST);

    }

    @Override
    public UserCarDetailsResponse updateCarDetails(CarUpdateRequest updateRequest) {

        UserCarDetails carDetails = carDetailsRepository.findById(updateRequest.getCarId())
                .orElseThrow(() -> new CarException("no such car found with id: "+ updateRequest.getCarId(), HttpStatus.BAD_REQUEST));

        carDetails.setRegistrationNumber(updateRequest.getRegisterNumber());
        carDetails.setModelName(updateRequest.getModelName());
        carDetails.setColor(updateRequest.getColor());
        carDetails.setSeatLimit(updateRequest.getSeatLimit());

        return toCarResponse(carDetailsRepository.save(carDetails));
    }

    private UserCarDetails getCarByRegistrationNumber(String registrationNumber) {

        return carDetailsRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new CarException(
                        "no such car found with given registration number: "
                                + registrationNumber, HttpStatus.NOT_FOUND)
                );
    }

    private UserCarDetails toCarEntity(CarRegisterRequest request, UserDetails details) {
        return UserCarDetails.builder()
                .registrationNumber(request.getRegisterNumber())
                .modelName(request.getModelName())
                .color(request.getColor())
                .seatLimit(request.getSeatLimit())
                .userDetails(details)
                .build();
    }

    private UserCarDetailsResponse toCarResponse(UserCarDetails saveCar) {
        return UserCarDetailsResponse.builder()
                .carId(saveCar.getCarId())
                .registerNumber(saveCar.getRegistrationNumber())
                .modelName(saveCar.getModelName())
                .color(saveCar.getColor())
                .seatLimit(saveCar.getSeatLimit())
                .build();
    }

}
