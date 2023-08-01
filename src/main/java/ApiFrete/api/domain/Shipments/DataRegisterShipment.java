package ApiFrete.api.domain.Shipments;
import ApiFrete.api.domain.Address.DataAdress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DataRegisterShipment(
        @NotNull
        Long weight,
        @NotNull
        String client_email,
        @NotNull
        @Valid
        DataAdress address,
        @NotNull
        Long zip_code_origin,
        @NotNull
        Long zip_code_destination,
        String shipment_status
) {

}
