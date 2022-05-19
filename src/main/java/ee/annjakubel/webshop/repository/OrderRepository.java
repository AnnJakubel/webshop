package ee.annjakubel.webshop.repository;

import java.util.List;
import ee.annjakubel.webshop.model.database.Order;
import ee.annjakubel.webshop.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByPersonOrderByCreationDateDesc(Person person);
}
