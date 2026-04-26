package application.demo.service;

import application.demo.dto.OrderRequest;
import application.demo.entity.OrderEntity;
import application.demo.entity.OrderItem;
import application.demo.repository.CartRepository;
import application.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public OrderEntity placeOrder(OrderRequest request) {

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }

        OrderEntity order = new OrderEntity();

        order.setUserId(request.getUserId());
        order.setAmount(request.getAmount());
        order.setPaymentId(request.getPaymentId());
        order.setAddress(request.getAddress());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> items = request.getItems().stream().map(dto -> {

            OrderItem item = new OrderItem();
            item.setProductName(dto.getProductName());
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());
            item.setOrder(order);

            return item;

        }).collect(Collectors.toList());

        order.setItems(items);

        OrderEntity saved = orderRepository.save(order);

        cartRepository.deleteAll(
                cartRepository.findByUserId(request.getUserId())
        );

        return saved;
    }

    // ✅ CORRECT PLACE (outside method)
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}