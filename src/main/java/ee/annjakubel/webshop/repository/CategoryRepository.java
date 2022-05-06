package ee.annjakubel.webshop.repository;

import ee.annjakubel.webshop.model.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
