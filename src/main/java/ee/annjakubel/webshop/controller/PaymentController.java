package ee.annjakubel.webshop.controller;

import ee.annjakubel.webshop.model.database.Person;
import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.model.request.input.CartProducts;
import ee.annjakubel.webshop.model.request.output.EveryPayUrl;
import ee.annjakubel.webshop.repository.PersonRepository;
import ee.annjakubel.webshop.service.OrderServiceImpl;
import ee.annjakubel.webshop.service.PaymentServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;
    //nagu new PaymentService() tekitamine
    //yks m2lukoht koguaeg

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    PersonRepository personRepository;

    @PostMapping("payment")
    public ResponseEntity<EveryPayUrl> getPaymentLink(@RequestBody List<Product> products) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = personRepository.getByEmail(email);
        log.info("Initialized payment link getting, {}", person.getPersonCode());

        List<Product> originalProducts = orderService.getAllProductsFromDb(products);
        double orderSum = orderService.calculateOrderSum(originalProducts);
        Long id = orderService.saveToDatabase(originalProducts, orderSum, person);
        return  ResponseEntity.ok()
                .body(paymentService.getPaymentLink(orderSum, id));
    }

    // order_reference=5413137&payment_reference=d26ba3ef85e607e4131f552b4977441c4c865427497f6183adbde78b6ccc68a2
    // order_reference=5413138&payment_reference=5d9f591cd34f68da654fa8bdd3ad9550de094e41050a28fe74c66143181a55bf
    @PostMapping("check-payment")
    public ResponseEntity<Boolean> checkIfPaid(@RequestParam Long orderId, @RequestParam String paymentRef) {
        // Kui on maksrud, muudan andmebaasis makstuks
        System.out.println(orderId);
        System.out.println(paymentRef);
        return ResponseEntity.ok().body(paymentService.checkIfOrderPaid(orderId, paymentRef));
    }

}
