package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;
import org.example.model.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String login;
    private User.Role role;
    private Vehicle rentedVehicle;

    public UserDto(String login, User.Role role) {
    }
}