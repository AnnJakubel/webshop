package ee.annjakubel.webshop.controller;

import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.service.OrderService;
import ee.annjakubel.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

   /* @PostMapping("check-payment")
    public boolean checkIfPaid() {
        // Kui on maksrud, muudan andmebaasis makstuks
        return true;
    }*/

}
