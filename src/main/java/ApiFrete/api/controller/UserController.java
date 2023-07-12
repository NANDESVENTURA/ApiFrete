package ApiFrete.api.controller;

import ApiFrete.api.domain.User.DataCreateUser;
import ApiFrete.api.domain.User.DataDetailingUser;
import ApiFrete.api.domain.User.User;
import ApiFrete.api.domain.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DataCreateUser data, UriComponentsBuilder uriBuilder) throws NoSuchAlgorithmException {
        try {
            UserDetails userExists = repository.findByEmail(data.email());
            System.out.println(userExists);
            if (userExists != null) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("ErrorMessage", "Email already registered");
                return ResponseEntity.badRequest().body(ErrorMessage);
            }
            var user = new User(data);
            repository.save(user);
            var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(new DataDetailingUser(user));
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }


}
