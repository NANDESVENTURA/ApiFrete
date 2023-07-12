package ApiFrete.api.domain.Address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String public_place;
    private String neighborhood;
    private String complement;
    private String number;
    private String state;
    private String city;


    public Address(DataAdress data) {
        this.public_place = data.public_place();
        this.neighborhood = data.neighborhood();
        this.complement = data.complement();
        this.number = data.number();
        this.state = data.state();
        this.city = data.city();
    }
}
