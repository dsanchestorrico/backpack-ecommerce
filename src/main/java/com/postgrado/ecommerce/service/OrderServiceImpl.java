package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.OrderItem;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user);

        Order orderSaved = orderRepository.save(order);
        return "Order saved successfully";
    }

    @Override
    public OrderDto getById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order",id));
        OrderDto orderDto = new OrderDto();
        orderDto.setComment(order.getComment());
        orderDto.setState(order.getState());
        orderDto.setTotalPrice(orderRepository.getTotalPrice(id));
        orderDto.setItems(orderRepository.getItemsWithTotalPrice(id));
        return orderDto;
    }
}
