package application.demo.controller;

import application.demo.entity.CartItem;
import application.demo.repository.CartRepository;
import application.demo.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ➕ ADD TO CART
    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {
        return cartService.addToCart(item);
    }

    // 📦 GET CART
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    // ➕ INCREASE QUANTITY
    @PostMapping("/increase/{id}")
    public CartItem increaseQty(@PathVariable Long id) {
        return cartService.increaseQty(id);
    }

    // ➖ DECREASE QUANTITY
    @PostMapping("/decrease/{id}")
    public CartItem decreaseQty(@PathVariable Long id) {
        return cartService.decreaseQty(id);
    }

    // ❌ DELETE ITEM
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        cartService.deleteItem(id);
    }

}