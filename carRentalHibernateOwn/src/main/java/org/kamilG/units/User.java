package org.kamilG.units;

import jakarta.persistence.*;

@Entity
@Table(name = "Users", schema = "lab")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOfRentedVehicle", referencedColumnName = "id")
    private Vehicle rentedVehicle;

    @Column(name = "adminPermission")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(int id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rentedVehicle = null;
        this.role = role;
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rentedVehicle = null;
        this.role = Role.USER;
    }

    public enum Role {
        USER, ADMIN;
    }

    public void setRentedVehicle(Vehicle rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }
}
