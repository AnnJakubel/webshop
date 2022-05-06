package ee.annjakubel.webshop.repository;

import ee.annjakubel.webshop.model.database.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

}
