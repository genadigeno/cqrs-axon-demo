package com.example.axondemo.aggregate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderDTO {
    UUID id;
    int amount;
}