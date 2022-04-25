package ee.annjakubel.webshop.model.request.output;

import ee.annjakubel.webshop.model.request.input.OmnivaParcelMachine;
import ee.annjakubel.webshop.model.request.input.SmartpostParcelMachine;
import lombok.Data;

import java.util.List;

@Data
public class ParcelMachines {
    private List<OmnivaParcelMachine> omnivaParcelMachines;
    private List<SmartpostParcelMachine> smartPostParcelMachines;
}
