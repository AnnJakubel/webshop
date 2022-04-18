package ee.annjakubel.webshop.model.output;

import ee.annjakubel.webshop.model.input.OmnivaParcelMachine;
import ee.annjakubel.webshop.model.input.SmartpostParcelMachine;
import lombok.Data;

import java.util.List;

@Data
public class ParcelMachines {
    private List<OmnivaParcelMachine> omnivaParcelMachines;
    private List<SmartpostParcelMachine> smartPostParcelMachines;
}
