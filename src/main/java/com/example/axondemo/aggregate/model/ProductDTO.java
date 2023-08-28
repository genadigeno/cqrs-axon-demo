package com.example.axondemo.aggregate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDTO {
    UUID id;
    String name;
    String description;
    int amount;
    double price;
}