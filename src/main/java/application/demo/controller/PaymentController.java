package application.demo.controller;

import application.demo.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public Map<String, Object> createPayment(@RequestParam double amount) throws Exception {
        return paymentService.createOrder(amount);
    }
}