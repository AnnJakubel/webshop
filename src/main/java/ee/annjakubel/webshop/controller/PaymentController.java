package ee.annjakubel.webshop.controller;

import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.service.OrderService;
import ee.annjakubel.webshop.service.PaymentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    //nagu new PaymentService() tekitamine
    //yks m2lukoht koguaeg

    @Autowired
    OrderService orderService;

    @PostMapping("payment")
    public ResponseEntity<String> getPaymentLink(@RequestBody List<Product> products) {
        // Tooted -- nimedega+hindadega
        // Maksma - Tellimuse nr-t
        //Salvestan andmebaasi -> maksmata kujul
        // V6tan andmebaasist tema ID (Mis on genereeritud)
        // ---> L2hen maksma
        // Kui on maksrud, muudan andmebaasis makstuks

        List<Product> originalProducts = orderService.getAllProductsFromDb(products);
        double orderSum = orderService.calculateOrderSum(originalProducts);
        Long id = orderService.saveToDatabase(originalProducts, orderSum);
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
