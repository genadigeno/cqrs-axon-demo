package com.example.axondemo.api.events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddStockEvent {
    public final UUID id;
    int amount;

    public AddStockEvent(UUID id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}