package ee.annjakubel.webshop.controller.exception;


import ee.annjakubel.webshop.model.database.Order;
import ee.annjakubel.webshop.model.database.Person;
import ee.annjakubel.webshop.repository.OrderRepository;
import ee.annjakubel.webshop.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = personRepository.getByEmail(email);
        log.info("Getting orders from {}", person.getPersonCode());

        return ResponseEntity.ok()
                .body(orderRepository.findAll());
    }
}
