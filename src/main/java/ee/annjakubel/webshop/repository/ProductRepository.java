package ee.annjakubel.webshop.repository;


import ee.annjakubel.webshop.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
