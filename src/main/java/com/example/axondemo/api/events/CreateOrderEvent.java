package com.example.axondemo.api.events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateOrderEvent {
    public final UUID id;
    int amount;

    public CreateOrderEvent(UUID id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}