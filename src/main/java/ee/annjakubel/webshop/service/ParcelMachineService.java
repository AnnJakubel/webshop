package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.model.request.input.SmartpostParcelMachine;
import ee.annjakubel.webshop.model.request.output.ParcelMachines;

import java.util.List;

public interface ParcelMachineService {

    ParcelMachines getParcelMachines(String country);
}
