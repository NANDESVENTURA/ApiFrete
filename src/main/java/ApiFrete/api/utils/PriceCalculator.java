package ApiFrete.api.utils;

import ApiFrete.api.Service.GoogleService;
import ApiFrete.api.domain.Shipments.DataRegisterShipment;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.IOException;


public class PriceCalculator {
    GoogleService distance;
    DataRegisterShipment dataRegisterShipment;

//    CONSTRUCTOR
//    public PriceCalculator(DataRegisterShipment data) {
//        this.dataRegisterShipment = data;
//    }
    public int calculatorPrice(GoogleService value, DataRegisterShipment data) throws IOException {
        var distance = value.getDistance(data);// value.getDistance(this.dataRegisterShipment)
        if (distance < 2500 ){
            return 150;
        }
        return 5 * (distance - 2000) + 150;
    }
}
