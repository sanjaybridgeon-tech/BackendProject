package application.demo.controller;

import application.demo.dto.OrderRequest;
import application.demo.entity.OrderEntity;
import application.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public OrderEntity placeOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }
}