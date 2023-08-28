package com.example.axondemo.query.repository;

import com.example.axondemo.query.model.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderDAO, UUID> {
}