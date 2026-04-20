package application.demo.service;

import application.demo.entity.CartItem;
import application.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // ➕ ADD TO CART
    public CartItem addToCart(CartItem item) {

        Optional<CartItem> existing =
                cartRepository.findByUserIdAndProductId(
                        item.getUserId(),
                        item.getProductId()
                );

        if (existing.isPresent()) {
            CartItem cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return cartRepository.save(cartItem);
        } else {
            item.setQuantity(1);
            return cartRepository.save(item);
        }
    }

    // 📦 GET CART
    public List<CartItem> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // ➕ INCREASE
    public CartItem increaseQty(Long id) {
        CartItem item = cartRepository.findById(id).orElseThrow();
        item.setQuantity(item.getQuantity() + 1);
        return cartRepository.save(item);
    }

    // ➖ DECREASE
    public CartItem decreaseQty(Long id) {
        CartItem item = cartRepository.findById(id).orElseThrow();

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
        }

        return cartRepository.save(item);
    }

    // ❌ DELETE
    public void deleteItem(Long id) {
        cartRepository.deleteById(id);
    }
}