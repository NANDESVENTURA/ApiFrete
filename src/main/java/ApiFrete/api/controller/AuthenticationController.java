package ApiFrete.api.controller;

import ApiFrete.api.domain.User.DataAuthentication;
import ApiFrete.api.domain.User.User;
import ApiFrete.api.infra.security.DataTokenJWT;
import ApiFrete.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthentication data) throws NoSuchAlgorithmException {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
