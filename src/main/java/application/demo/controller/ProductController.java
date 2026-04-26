package application.demo.controller;

import application.demo.entity.Product;
import application.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 📦 Get all / search
    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String search) {

        if (search != null && !search.trim().isEmpty()) {
            return productService.searchProducts(search);
        }

        return productService.getAllProducts();
    }

    // ➕ Add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // 🔍 Get by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // ❌ Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}