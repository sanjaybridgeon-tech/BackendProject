package application.demo.repository;

import application.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // ✅ Existing (keep it)
    List<Product> findByNameContainingIgnoreCase(String name);

    // ✅ NEW (for pagination)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}