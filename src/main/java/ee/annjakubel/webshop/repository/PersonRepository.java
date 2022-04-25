package ee.annjakubel.webshop.repository;

import ee.annjakubel.webshop.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getByEmail(String email);
}