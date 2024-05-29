package org.kamilG.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kamilG.demo.units.User;
import org.kamilG.demo.units.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private String username;
  private User.Role role;
  private Vehicle rentedVehicle;
}
