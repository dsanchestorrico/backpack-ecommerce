package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.OrderItem;
import com.postgrado.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private ProductService productService;
    private final OrderRepository orderRepository;

    @Override
    public String create(OrderDto dto) {
        Order order = new Order();
        order.setComment(dto.getComment());

        //map crea una lista de nuevos objetos
        List<OrderItem> items = dto.getItems().stream().map((itemDto)->{
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setProduct(productService.getById(itemDto.getProductId()));
            orderItem.setOrder(order);
            return orderItem;
        }).toList();

        order.setItems(items);
        //TODO: set user

        Order orderSaved = orderRepository.save(order);
        return "Order saved successfully";
    }
}
