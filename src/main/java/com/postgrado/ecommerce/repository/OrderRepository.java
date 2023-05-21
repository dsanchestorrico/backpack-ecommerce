package com.postgrado.ecommerce.repository;

import com.postgrado.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
