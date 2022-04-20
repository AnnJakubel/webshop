package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.model.input.EveryPayResponse;
import ee.annjakubel.webshop.model.output.EveryPayData;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@Log4j2
public class PaymentService {

    @Value("${everypay.baseurl}")
    String everyPayBaseUrl;

    @Value("${everypay.credentials}")
     String credentials;

    @Value("${everypay.username}")
    String username;

    @Value("${everypay.account}")
    String account;

    @Value("${everypay.customerurl}")
    String customerUrl;

    @Autowired
    RestTemplate restTemplate;

    public String getPaymentLink(double amount, Long orderId) {
        EveryPayData everyPayData = buildEveryPayData(amount, orderId);

        String url = everyPayBaseUrl + "/payments/oneoff";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + credentials);

        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData, headers);

        //yks ja sama new koguaeg --> @Autowired
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, EveryPayResponse.class);
        if(response.getStatusCodeValue() == 201 && response.getBody() != null) {
            return response.getBody().getPayment_link();
        }

        return "";
    }

    private EveryPayData buildEveryPayData(double amount, Long orderId) {
        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username(username);
        everyPayData.setAccount_name(account);
        everyPayData.setAmount(amount);
        everyPayData.setOrder_reference(orderId.toString());
        everyPayData.setNonce("ad" + Math.random() + new Date());
        everyPayData.setTimestamp(ZonedDateTime.now().toString());
        System.out.println(new Date().toString());
        log.info(new Date().toString());
        log.error("ERROR!");
        log.debug("asd");//log4j2
        everyPayData.setCustomer_url(customerUrl); //serverisse yles heroku --
        // java ja front-end(Angular/React)
        return everyPayData;
    }
}
