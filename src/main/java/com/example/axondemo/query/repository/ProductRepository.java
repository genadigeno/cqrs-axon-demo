package com.example.axondemo.query.repository;

import com.example.axondemo.query.model.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductDAO, UUID> {
}