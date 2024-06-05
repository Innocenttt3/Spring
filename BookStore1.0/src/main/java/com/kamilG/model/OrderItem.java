package com.kamilG.model;

import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int quantity;

  @ManyToOne private Book book;
}
