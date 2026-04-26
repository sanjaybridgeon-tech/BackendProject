package application.demo.dto;

import application.demo.entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Long userId;
    private double amount;
    private String paymentId;
    private Address address;

    // 🔥 ADD THIS
    private List<OrderItemDTO> items;
}