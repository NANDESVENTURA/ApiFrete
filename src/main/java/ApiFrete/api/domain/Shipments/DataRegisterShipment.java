package ApiFrete.api.domain.Shipments;
import ApiFrete.api.domain.Address.DataAdress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record DataRegisterShipment(
        @NotNull
        Long weight,
        @NotNull
        @Valid
        DataAdress address,
        @NotNull
        Long zip_code_origin,
        @NotNull
        Long zip_code_destination
) {
}
