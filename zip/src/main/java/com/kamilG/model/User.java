package com.kamilG.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
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
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();
}
