package com.postgrado.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;
    private LocalDateTime date = LocalDateTime.now();
    private String comment;
    @Enumerated(value = EnumType.STRING)
    private OrderState state = OrderState.PENDING;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST) //mapeado por el atributo "order" definido en OrderItem + guardado en cascada
    List<OrderItem> items;
}
