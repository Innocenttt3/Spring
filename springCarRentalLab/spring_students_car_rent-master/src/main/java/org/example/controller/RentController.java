package org.example.controller;

import org.example.dto.RentCarDto;
import org.example.service.RentService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rents")
public class RentController {


    private final RentService rentService;
    private final UserService userService;

    public RentController(RentService rentService, UserService userService) {
        this.rentService = rentService;
        this.userService = userService;
    }


    @GetMapping("{login}")
    public ResponseEntity<RentCarDto> getVehicle(@PathVariable String login) {
        RentCarDto rentCarDto = rentService.getRentedCar(login);
        if (rentCarDto != null) {
            return ResponseEntity.ok(rentCarDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/rent")
    public ResponseEntity<String> rentVehicle(@RequestBody RentCarDto request) {
        boolean success = rentService.rentVehicle(request.getPlate(), request.getLogin());
        if (success) {
            return ResponseEntity.ok("Vehicle rented");
        } else {
            return ResponseEntity.badRequest().body("Failed");
        }
    }
}