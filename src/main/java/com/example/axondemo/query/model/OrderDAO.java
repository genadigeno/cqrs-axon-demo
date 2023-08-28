package com.example.axondemo.query.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderDAO {

    @Id
    UUID id;

    @Column(nullable = false)
    UUID productId;

    @Column(nullable = false)
    int amount;

    @Column(nullable = false)
    double price;


    @Column(nullable = false)
    double totalPrice;

    @Column(nullable = false)
    long timestamp;
}