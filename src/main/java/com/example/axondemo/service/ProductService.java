package com.example.axondemo.service;

import com.example.axondemo.aggregate.model.ProductDTO;
import com.example.axondemo.api.commands.AddProductCommand;
import com.example.axondemo.api.commands.AddStockCommand;
import com.example.axondemo.api.commands.GetProductsCommand;
import com.example.axondemo.query.model.ProductDAO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;


    public ProductService(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    public String addProduct(ProductDTO productDTO) {
        AddProductCommand addProductCommand = new AddProductCommand(UUID.randomUUID(), productDTO.getAmount(),
                productDTO.getPrice(), productDTO.getName(), productDTO.getDescription());
        commandGateway.send(addProductCommand);
        return "Product is added successfully";
    }


    public String addStock(ProductDTO productDTO) {
        AddStockCommand addStockCommand = new AddStockCommand(productDTO.getId(), productDTO.getAmount());
        commandGateway.send(addStockCommand);
        return "Stock is added successfully.";
    }


    public CompletableFuture<List<ProductDAO>> getProducts(int page, int pageSize) {
        return queryGateway.query(new GetProductsCommand(page, pageSize),
                ResponseTypes.multipleInstancesOf(ProductDAO.class));
    }
}