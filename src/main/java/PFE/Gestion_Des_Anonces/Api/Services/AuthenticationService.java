package PFE.Gestion_Des_Anonces.Api.Services;

import PFE.Gestion_Des_Anonces.Api.Config.JwtService;
import PFE.Gestion_Des_Anonces.Api.Models.DTO_CLASSES.LOGIN_REQUEST_DTO;
import PFE.Gestion_Des_Anonces.Api.Models.Role.RoleRepository;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import PFE.Gestion_Des_Anonces.Api.Models.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;



    public ResponseEntity<String> register(User request) {
        System.out.println("Registring !");
        List<User> userList = userRepository.findByEmail(request.getEmail());
        boolean UserExists = userList.size() == 1;
        if(UserExists) {//exists
            return ResponseEntity.status(401).build();
        }
        User user = User.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleRepository.findByName("ROLE_USER"))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok().header("Authorization", jwtToken).build();
        }

    public ResponseEntity<String> authenticate(LOGIN_REQUEST_DTO request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );


        List<User> userList = userRepository.findByEmail(request.email());
        if(userList.size() == 0){
            return ResponseEntity.status(401).build();
        }
        System.out.println("User Found !");
        User user = userList.get(0);
        if(user != null){
            boolean samePassword = passwordEncoder.matches(request.password(),user.getPassword());
            if(!samePassword)return ResponseEntity.status(401).build();
            System.out.println("Password Verified !");
            String Token = jwtService.generateToken(user);
            return ResponseEntity.ok().header("Authorization", Token).build();
        }
        return ResponseEntity.status(401).build();
    }

}
