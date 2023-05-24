package com.postgrado.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {
    private Integer quantity;
    private UUID productId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    //creado para poder asignarlo como PJO al query
    public OrderItemDto(UUID productId,Integer quantity, Double totalPrice) {
        this.quantity = quantity;
        this.productId = productId;
        this.totalPrice = totalPrice;
    }
}
