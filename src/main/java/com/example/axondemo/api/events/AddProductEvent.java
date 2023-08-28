package com.example.axondemo.api.events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddProductEvent {
    public final UUID id;
    int amount;
    double price;
    String name;
    String description;

    public AddProductEvent(UUID id, int amount, double price, String name, String description) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.name = name;
        this.description = description;
    }
}