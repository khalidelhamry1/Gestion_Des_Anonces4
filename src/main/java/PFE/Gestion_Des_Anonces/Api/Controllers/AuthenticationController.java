package PFE.Gestion_Des_Anonces.Api.Controllers;

import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.LOGIN_REQUEST_DTO;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import PFE.Gestion_Des_Anonces.Api.Services.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/Auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody @NonNull User request
    ) {
        return authenticationService.register(request);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody @NonNull LOGIN_REQUEST_DTO request
    ) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/VerifyToken")
    public ResponseEntity<Boolean> isExpired(@RequestBody String token){
        System.out.print(token);
        return authenticationService.isTokenValid(token);
    }

}
