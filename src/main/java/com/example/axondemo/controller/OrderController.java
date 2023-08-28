package com.example.axondemo.controller;

import com.example.axondemo.aggregate.model.OrderDTO;
import com.example.axondemo.query.model.OrderDAO;
import com.example.axondemo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> orderProduct(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.orderProduct(orderDTO), HttpStatus.OK);
    }

    @GetMapping("/{page}/{pageSize}")
    public CompletableFuture<List<OrderDAO>> getOrders(@PathVariable int page, @PathVariable int pageSize) {
        return orderService.getOrders(page, pageSize);
    }
}