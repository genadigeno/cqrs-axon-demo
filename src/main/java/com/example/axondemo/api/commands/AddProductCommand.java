package com.example.axondemo.api.commands;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@Setter
public class AddProductCommand {
    @TargetAggregateIdentifier
    public final UUID id;
    int amount;
    double price;
    String name;
    String description;

    public AddProductCommand(UUID id, int amount, double price, String name, String description) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.name = name;
        this.description = description;
    }
}