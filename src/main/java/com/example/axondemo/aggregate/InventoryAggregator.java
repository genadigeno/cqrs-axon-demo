package com.example.axondemo.aggregate;

import com.example.axondemo.api.commands.AddProductCommand;
import com.example.axondemo.api.commands.AddStockCommand;
import com.example.axondemo.api.commands.CreateOrderCommand;
import com.example.axondemo.api.events.AddProductEvent;
import com.example.axondemo.api.events.AddStockEvent;
import com.example.axondemo.api.events.CreateOrderEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(cache = "inventoryCache")
public class InventoryAggregator {
    String name;
    String description;
    int amount;
    double price;
    @AggregateIdentifier
    private UUID productId;

    @CommandHandler
    public InventoryAggregator(AddProductCommand command) {
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("No amount specified.");
        }
        productId = command.getId();
        name = command.getName();
        description = command.getDescription();
        amount = command.getAmount();
        price = command.getPrice();
        apply(new AddProductEvent(command.getId(), command.getAmount(), command.getPrice(), command.getName(),
                command.getDescription()));
    }

    public InventoryAggregator() {
// Required by Axon to construct an empty instance to initiate Event Sourcing.
    }

    @CommandHandler
    public void handle(CreateOrderCommand command) {
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("No amount specified.");
        }
        if (amount < command.getAmount()) {
            throw new IllegalArgumentException("Not enough item in stock.");
        }
        apply(new CreateOrderEvent(productId, command.getAmount()));
    }

    @CommandHandler
    public void handle(AddStockCommand command) {
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("No amount specified.");
        }
        apply(new AddStockEvent(productId, command.getAmount()));
    }

    @EventSourcingHandler
    public void on(AddProductEvent event) {
        productId = event.getId();
        name = event.getName();
        description = event.getDescription();
        amount = event.getAmount();
        price = event.getPrice();
    }

    @EventSourcingHandler
    public void on(CreateOrderEvent event) {
        amount -= event.getAmount();
    }

    @EventSourcingHandler
    public void on(AddStockEvent event) {
        amount += event.getAmount();
    }
}