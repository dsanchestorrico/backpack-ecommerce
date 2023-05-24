package com.postgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postgrado.ecommerce.entity.OrderState;
import lombok.Data;

import java.util.List;
@Data
public class OrderDto {
    private String comment;
    private List<OrderItemDto>items;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderState state;
}
