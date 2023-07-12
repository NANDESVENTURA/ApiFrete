package ApiFrete.api.domain.Shipments;


import ApiFrete.api.domain.Address.Address;
import ApiFrete.api.domain.User.User;
import ApiFrete.api.utils.PriceCalculator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

@Table(name = "shipments")
@Entity(name = "Shipment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Shipment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long weight;

    @Embedded
    private Address address;

    private Long zip_code_origin;
    private Long zip_code_destination;
    private Integer price;

    public Shipment(DataRegisterShipment data, User logged, Integer price) {
        this.user_id = logged.getId();
        this.weight = data.weight();
        this.address = new Address(data.address());
        this.zip_code_origin = data.zip_code_origin();
        this.zip_code_destination = data.zip_code_destination();
        this.price = price;
    }


}
