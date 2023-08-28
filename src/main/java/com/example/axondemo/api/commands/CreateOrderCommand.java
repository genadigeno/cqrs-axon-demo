package com.example.axondemo.api.commands;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@Setter
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    public final UUID id;
    int amount;

    public CreateOrderCommand(UUID id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}