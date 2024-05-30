package org.kamilG.demo.controller;

import java.util.Collection;
import org.kamilG.demo.dto.CreateUserDto;
import org.kamilG.demo.dto.UserDto;
import org.kamilG.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/all")
  public ResponseEntity<Collection<UserDto>> getAllUsers() {
    Collection<UserDto> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("{login}")
  public ResponseEntity<UserDto> getUser(@PathVariable("login") String login) {
    UserDto returnedUser = userService.getUserByLogin(login);
    if (returnedUser == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(returnedUser);
    }
  }

  @PostMapping()
  public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
    if (userService.createUser(createUserDto)) {
      return ResponseEntity.ok(new UserDto());
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("{login}")
  public ResponseEntity<?> deleteUser(@PathVariable("login") String login) {
    String result = userService.deleteUser(login);
    return switch (result) {
      case "user removed" -> ResponseEntity.ok().body(result);
      case "vehicle is not null" -> ResponseEntity.badRequest().body(result);
      case "not found" -> ResponseEntity.notFound().build();
      default ->
          ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An unexpected error occurred");
    };
  }
}
