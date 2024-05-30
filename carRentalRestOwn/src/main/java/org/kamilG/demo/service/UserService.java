package org.kamilG.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.kamilG.demo.dao.IUserRepo;
import org.kamilG.demo.dto.CreateUserDto;
import org.kamilG.demo.dto.UserDto;
import org.kamilG.demo.units.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {


  private final IUserRepo userRepo;

  public Collection<UserDto> getAllUsers() {
    Collection<UserDto> usersDto = new ArrayList<>();
    Collection<User> users = userRepo.getUsers();
    for (User user : users) {
      UserDto userDto = new UserDto(user.getLogin(), user.getRole(), user.getRentedVehicle());
      usersDto.add(userDto);
    }
    return usersDto;
  }

  public UserDto getUserByLogin(String login) {
    System.out.println("login");
    User user = userRepo.getUser(login);
    if (user != null) {
      return new UserDto(user.getLogin(), user.getRole(), user.getRentedVehicle());
    } else {
      return null;
    }
  }

  public boolean createUser(CreateUserDto createUserDto) {
    User newUser = new User();
    newUser.setLogin(createUserDto.getLogin());
    newUser.setPassword(createUserDto.getPassword());
    newUser.setRole(User.Role.USER);
    if (userRepo.addUser(newUser)) {
      System.out.println("user added");
      return true;
    } else {
      System.out.println("user not added");
      return false;
    }
  }

  public String deleteUser(String login) {
    User userToDelete = userRepo.getUser(login);
    if (userToDelete == null) {
      return "not found";
    } else if (userToDelete.getRentedVehicle() != null) {
      return "vehicle is not null";
    } else {
      userRepo.removeUser(login);
      return "user removed";
    }
  }
}
