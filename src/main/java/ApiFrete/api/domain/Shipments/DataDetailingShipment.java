package ApiFrete.api.domain.Shipments;

import ApiFrete.api.domain.Address.Address;
import ApiFrete.api.utils.PriceCalculator;


import java.math.BigInteger;

public record DataDetailingShipment(Long id, Long user_id, Long weight, Address address, Long zip_code_origin, Long zip_code_destination, Integer price) {
    public DataDetailingShipment(Shipment shipment) {
        this(shipment.getId(),shipment.getUser_id(),shipment.getWeight(),shipment.getAddress(),shipment.getZip_code_origin(),shipment.getZip_code_destination(),shipment.getPrice());
    }


}
