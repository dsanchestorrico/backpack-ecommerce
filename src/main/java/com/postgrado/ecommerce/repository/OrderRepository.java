package com.postgrado.ecommerce.repository;

import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "SELECT SUM(products.price * order_items.quantity) " +
            " FROM orders " +
            " JOIN order_items ON orders.id = order_items.order_id " +
            " JOIN products ON order_items.product_id = products.id " +
            " WHERE orders.id = ?1 ", nativeQuery = true)
    Double getTotalPriceByOrderById(String orderId); //cambiado de UUID a String por ser native

    @Query(value = "SELECT  SUM(p.price * oi.quantity) " +
            " FROM Order o " +
            " JOIN o.items oi " +
            " JOIN oi.product p " +
            " WHERE o.id = ?1 ", nativeQuery = false)
    Double getTotalPrice(UUID orderId);

    //aqui se esta retornando una nueva estructura usar un POJO o DTO
    @Query(value = "SELECT new com.postgrado.ecommerce.dto.OrderItemDto(p.id, oi.quantity, p.price * oi.quantity) " +
            " FROM Order o " +
            " JOIN o.items oi " +
            " JOIN oi.product p " +
            " WHERE o.id = ?1 ", nativeQuery = false)
    List<OrderItemDto> getItemsWithTotalPrice(UUID id);

}
