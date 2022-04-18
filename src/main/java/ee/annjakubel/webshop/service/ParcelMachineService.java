package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.model.input.OmnivaParcelMachine;
import ee.annjakubel.webshop.model.input.SmartpostParcelMachine;
import ee.annjakubel.webshop.model.output.ParcelMachines;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ParcelMachineService {
    String omnivaUrl = "https://www.omniva.ee/locations.json";
    String smartpostUrl = "https://www.smartpost.ee/places.json";

    @Autowired
    RestTemplate restTemplate;

    public ParcelMachines getParcelMachines(String country) {
        ParcelMachines parcelMachines = new ParcelMachines();
        parcelMachines.setOmnivaParcelMachines(fetchOmnivaParcelMachines(country));
        if (country.equals("EE")) {
            parcelMachines.setSmartPostParcelMachines(fetchSmartpostParcelMachines());
        } else {
            parcelMachines.setSmartPostParcelMachines(new ArrayList<>()); //tyhi list
        }
        return parcelMachines;

    }

    private List<SmartpostParcelMachine> fetchSmartpostParcelMachines() {
        ResponseEntity<SmartpostParcelMachine[]> response = null;
        List<SmartpostParcelMachine> smartpostParcelMachines = new ArrayList<>();
        try {
            response = restTemplate
                    .exchange(smartpostUrl, HttpMethod.GET, null, SmartpostParcelMachine[].class);
            if (response.getBody() != null) {
                smartpostParcelMachines = Arrays.asList(response.getBody());
            }
        } catch (RestClientException e) {
            log.error("Smartpost API endpointiga ei saanud yhendust");
        }

        return smartpostParcelMachines;
    }


    private List<OmnivaParcelMachine> fetchOmnivaParcelMachines(String country) {
        ResponseEntity<OmnivaParcelMachine[]> response = restTemplate
                .exchange(omnivaUrl, HttpMethod.GET, null, OmnivaParcelMachine[].class);

        List<OmnivaParcelMachine> omnivaParcelMachines = new ArrayList<>();
        if (response.getBody() != null) {
            omnivaParcelMachines = Arrays.asList(response.getBody());
            omnivaParcelMachines = omnivaParcelMachines.stream()
                    .filter(p -> p.getA0_NAME().equals(country))
                    .collect(Collectors.toList());
            //Sama v6ib ka for-each loopiga teha
        }
        return omnivaParcelMachines;
    }
}
