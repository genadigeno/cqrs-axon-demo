package com.example.axondemo.service;

import com.example.axondemo.aggregate.model.OrderDTO;
import com.example.axondemo.api.commands.CreateOrderCommand;
import com.example.axondemo.api.commands.GetOrdersCommand;
import com.example.axondemo.query.model.OrderDAO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;


    public OrderService(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    public String orderProduct(OrderDTO orderDTO) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(orderDTO.getId(), orderDTO.getAmount());
        commandGateway.send(createOrderCommand);
        return "Order is placed successfully.";
    }


    public CompletableFuture<List<OrderDAO>> getOrders(int page, int pageSize) {
        return queryGateway.query(new GetOrdersCommand(page, pageSize),
                ResponseTypes.multipleInstancesOf(OrderDAO.class));
    }
}