package ApiFrete.api.domain.Shipments;

import ApiFrete.api.domain.Address.Address;

public record DataDetailingShipment(Long id, Long user_id, String client_email, Long weight, Address address, Long zip_code_origin, Long zip_code_destination, Integer price, ShipmentStatus shipment_status) {
    public DataDetailingShipment(Shipment shipment) {
        this(shipment.getId(),shipment.getUser_id(),shipment.getClient_email(),shipment.getWeight(),shipment.getAddress(),shipment.getZip_code_origin(),shipment.getZip_code_destination(),shipment.getPrice(), shipment.getShipment_status());
    }


}
