package com.kamilG.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labjpa.users", schema = "labjpa")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Nazwa uzytkownika jest wymagana")
  private String username;
  @NotBlank(message = "Haslo jest wymagane")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "labjpa.user_roles",
          schema = "labjpa",
          joinColumns = @JoinColumn(name = "labjpa.user_id"),
          inverseJoinColumns = @JoinColumn(name = "labjpa.role_id")
  )
  private Set<Role> roles = new HashSet<>();

  public @NotBlank(message = "Haslo jest wymagane") String getPassword() {
    return password;
  }

  public @NotBlank(message = "Nazwa uzytkownika jest wymagana") String getUsername() {
    return username;
  }

  public Set<Role> getRoles() {
    return roles;
  }
}
