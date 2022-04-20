package ee.annjakubel.webshop.repository;

import ee.annjakubel.webshop.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
