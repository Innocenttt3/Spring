package com.kamilG.model;

import jakarta.persistence.*;

@Entity
@Table(name = "labjpa.roles", schema = "labjpa")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }
}
