package ApiFrete.api.controller;


import ApiFrete.api.Service.GoogleService;
import ApiFrete.api.domain.Shipments.DataDetailingShipment;
import ApiFrete.api.domain.Shipments.DataRegisterShipment;
import ApiFrete.api.domain.Shipments.Shipment;
import ApiFrete.api.domain.Shipments.ShipmentsRepository;
import ApiFrete.api.domain.User.User;
import ApiFrete.api.utils.PriceCalculator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/shipments")
public class ShipmentsController {
    @Autowired
    private ShipmentsRepository repository;

//    private PriceCalculator priceCalculator;


    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterShipment data, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal User logged) throws NoSuchAlgorithmException {
        try {
            var googleApi = new GoogleService();
            var priceCalculator = new PriceCalculator(); //new PriceCalculator(data);
            var price = priceCalculator.calculatorPrice(googleApi,data); //priceCalculator.calculatorPrice(googleApi)
            var shipment = new Shipment(data, logged, price);
//            repository.save(shipment);
            var uri = uriBuilder.path("/shipments/{id}").buildAndExpand(shipment.getId()).toUri();
            return ResponseEntity.created(uri).body(new DataDetailingShipment(shipment));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
