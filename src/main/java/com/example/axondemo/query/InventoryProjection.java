package com.example.axondemo.query;

import com.example.axondemo.api.commands.GetOrdersCommand;
import com.example.axondemo.api.commands.GetProductsCommand;
import com.example.axondemo.api.events.AddProductEvent;
import com.example.axondemo.api.events.AddStockEvent;
import com.example.axondemo.api.events.CreateOrderEvent;
import com.example.axondemo.query.model.OrderDAO;
import com.example.axondemo.query.model.ProductDAO;
import com.example.axondemo.query.repository.OrderRepository;
import com.example.axondemo.query.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryProjection {

    ProductRepository productRepository;

    OrderRepository orderRepository;


    public InventoryProjection(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(AddProductEvent event, @Timestamp Instant timestamp) {
        ProductDAO product = new ProductDAO(event.getId(), event.getName(), event.getDescription(), event.getAmount(),
                event.getPrice(), timestamp.getEpochSecond());
        productRepository.save(product);
    }

    @EventHandler
    public void on(CreateOrderEvent event, @Timestamp Instant timestamp) {
        Optional<ProductDAO> productOpt = productRepository.findById(event.getId());
        if (productOpt.isPresent()) {
            ProductDAO productDAO = productOpt.get();
            productDAO.setAmount(productDAO.getAmount() - event.getAmount());
            productDAO.setLastUpdateEpoch(timestamp.getEpochSecond());
            productRepository.save(productDAO);
            OrderDAO order = new OrderDAO(UUID.randomUUID(), productDAO.getId(), event.getAmount(),
                    productDAO.getPrice(), event.getAmount() * productDAO.getPrice(), timestamp.getEpochSecond());
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product not exist.");
        }
    }

    @EventHandler
    public void on(AddStockEvent event, @Timestamp Instant timestamp) {
        Optional<ProductDAO> productOpt = productRepository.findById(event.getId());
        if (productOpt.isPresent()) {
            ProductDAO productDAO = productOpt.get();
            productDAO.setAmount(productDAO.getAmount() + event.getAmount());
            productDAO.setLastUpdateEpoch(timestamp.getEpochSecond());
            productRepository.save(productDAO);
        } else {
            throw new IllegalArgumentException("Product not exist.");
        }
    }

    @QueryHandler
    public List<ProductDAO> handle(GetProductsCommand query) {
        return productRepository.findAll(PageRequest.of(query.getPage(), query.getPageSize())).getContent();
    }

    @QueryHandler
    public List<OrderDAO> handle(GetOrdersCommand query) {
        return orderRepository.findAll(PageRequest.of(query.getPage(), query.getPageSize())).getContent();
    }
}