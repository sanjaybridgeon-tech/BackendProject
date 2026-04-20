package application.demo.service;

import application.demo.dto.OrderRequest;
import application.demo.entity.OrderEntity;
import application.demo.repository.CartRepository;
import application.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public OrderEntity placeOrder(OrderRequest request) {

        OrderEntity order = new OrderEntity();

        order.setUserId(request.getUserId());
        order.setAmount(request.getAmount());
        order.setPaymentId(request.getPaymentId());
        order.setAddress(request.getAddress());
        order.setCreatedAt(LocalDateTime.now());

        OrderEntity saved = orderRepository.save(order);

        // 🔥 Clear cart after order
        cartRepository.deleteAll(cartRepository.findByUserId(request.getUserId()));

        return saved;
    }
}