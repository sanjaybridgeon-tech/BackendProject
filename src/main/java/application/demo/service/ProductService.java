package application.demo.service;

import application.demo.entity.Product;
import application.demo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    public List<Product> searchProducts(String search) {
        return productRepository.findByNameContainingIgnoreCase(search);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public Page<Product> getProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
    public Page<Product> searchProducts(String keyword, int page, int size) {
        return productRepository.findByNameContainingIgnoreCase(
                keyword,
                PageRequest.of(page, size)
        );
}}