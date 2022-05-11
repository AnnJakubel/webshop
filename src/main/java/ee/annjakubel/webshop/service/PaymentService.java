package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.model.request.output.EveryPayUrl;

public interface PaymentService {

    EveryPayUrl getPaymentLink(double amount, Long orderId);

    Boolean checkIfOrderPaid(Long orderId, String paymentRef);


}
