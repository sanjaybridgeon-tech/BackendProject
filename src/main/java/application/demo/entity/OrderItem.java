package application.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private double price;

    // 🔗 Many items belong to ONE order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}