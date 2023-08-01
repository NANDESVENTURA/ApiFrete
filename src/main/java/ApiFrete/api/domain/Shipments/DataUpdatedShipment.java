package ApiFrete.api.domain.Shipments;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record DataUpdatedShipment(

        @NotNull
        Long id,

        ShipmentStatus shipment_status
) {
}
