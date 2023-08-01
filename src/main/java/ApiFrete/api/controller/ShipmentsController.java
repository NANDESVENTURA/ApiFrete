package ApiFrete.api.controller;


import ApiFrete.api.Service.EmailService;
import ApiFrete.api.Service.GoogleService;
import ApiFrete.api.domain.Shipments.*;
import ApiFrete.api.domain.User.User;
import ApiFrete.api.utils.PriceCalculator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/shipments")
public class ShipmentsController {
    @Autowired
    private ShipmentsRepository repository;

    @Autowired
    EmailService emailService;
//    private PriceCalculator priceCalculator;


    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterShipment data, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal User logged, Value("${api.service.google.key.secret}") String googleKey) throws NoSuchAlgorithmException {
        try {
            var googleApi = new GoogleService();
            var priceCalculator = new PriceCalculator(); //new PriceCalculator(data);
            var price = priceCalculator.calculatorPrice(googleApi, data, googleKey); //priceCalculator.calculatorPrice(googleApi)
            var shipment = new Shipment(data, logged, price);
            repository.save(shipment);
            var uri = uriBuilder.path("/shipments/{id}").buildAndExpand(shipment.getId()).toUri();

            emailService.sendEmail(shipment.getClient_email(), shipment.getId(), shipment.getShipment_status());

            return ResponseEntity.created(uri).body(new DataDetailingShipment(shipment));

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        try {
            Optional<Shipment> shipmentExists = repository.findById(id);
            if (shipmentExists.isEmpty()) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("ErrorMessage", "Shipment not found");
                return ResponseEntity.badRequest().body(ErrorMessage);
            }
            var shipment = repository.getReferenceById(id);
            return ResponseEntity.ok(new DataDetailingShipment(shipment));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }
    @PutMapping
    @Transactional
    public ResponseEntity updated(@RequestBody @Valid DataUpdatedShipment data) {
        try {
            var shipment = repository.getReferenceById(data.id());
            shipment.updatedStatus(data);

            emailService.sendEmail(shipment.getClient_email(), shipment.getId(), shipment.getShipment_status());

            return ResponseEntity.ok(new DataDetailingShipment(shipment));
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }
}
