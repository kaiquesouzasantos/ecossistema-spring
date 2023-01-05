package modules.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import modules.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
