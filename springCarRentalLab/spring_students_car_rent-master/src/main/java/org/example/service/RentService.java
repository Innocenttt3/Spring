package org.example.service;

import org.example.dao.IUserRepository;
import org.example.dao.IVehicleRepository;
import org.example.dto.RentCarDto;
import org.example.model.User;
import org.example.model.Vehicle;
import org.springframework.stereotype.Service;


@Service
public class RentService {

    private final IUserRepository userRepository;

    private final IVehicleRepository vehicleRepository;

    public RentService(IUserRepository userRepository, IVehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public boolean rentVehicle(String plate, String login) {
        User user = userRepository.getUser(login);
        Vehicle vehicle = vehicleRepository.getVehicle(plate);

        if (user != null && vehicle != null && !vehicle.isRent()) {
            vehicleRepository.rentVehicle(plate, login);
            return true;
        }
        return false;
    }

    public RentCarDto getRentedCar(String plate) {
        System.out.println("plate");
        Vehicle vehicle = vehicleRepository.getVehicle(plate);
        if (vehicle != null) {
            return new RentCarDto(vehicle.getPlate(), vehicle.getUser().getLogin());
        } else {
            return null;
        }
    }
}