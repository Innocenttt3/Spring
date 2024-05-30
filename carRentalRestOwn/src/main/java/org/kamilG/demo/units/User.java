package org.kamilG.demo.units;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Setter
@Entity
@Table(name = "Users", schema = "lab")
public class User {
  @Id private String login;
  private String password;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idOfRentedVehicle", referencedColumnName = "id")
  private Vehicle rentedVehicle;

  @Enumerated(EnumType.STRING)
  @JdbcType(PostgreSQLEnumJdbcType.class)
  @Column(name = "adminpermission")
  private Role role;

  public User() {}

  public User(String login, String password) {
    this.login = login;
    this.password = password;
    this.rentedVehicle = null;
    this.role = Role.USER;
  }

  public enum Role {
    USER,
    ADMIN;
  }

  public void setRentedVehicle(Vehicle rentedVehicle) {
    this.rentedVehicle = rentedVehicle;
  }

  public Vehicle getRentedVehicle() {
    return rentedVehicle;
  }

  public Role getRole() {
    return role;
  }

  public String getPassword() {
    return password;
  }

  public String getLogin() {
    return login;
  }

  @Override
  public String toString() {
    return "login: " + login + ", password: " + password + ", role: " + role;
  }
}
