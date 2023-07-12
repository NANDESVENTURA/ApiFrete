package ApiFrete.api.domain.Address;

import jakarta.validation.constraints.NotBlank;

public record DataAdress(
        @NotBlank
        String public_place,

        @NotBlank
        String neighborhood,

        String complement,
        String number,
        @NotBlank
        String state,
        @NotBlank
        String city ) {
}
