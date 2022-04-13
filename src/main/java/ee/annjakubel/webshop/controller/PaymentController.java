package ee.annjakubel.webshop.controller;

import ee.annjakubel.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    //nagu new PaymentService() tekitamine
    //yks m2lukoht koguaeg

    @PostMapping("payment")
    public String getPaymentLink(@RequestBody String amount) {
        System.out.println(paymentService);
        return paymentService.getPaymentLink(amount);
    }
}
