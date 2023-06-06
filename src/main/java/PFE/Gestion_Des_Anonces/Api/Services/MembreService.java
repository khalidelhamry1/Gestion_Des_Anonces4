package PFE.Gestion_Des_Anonces.Api.Services;

import PFE.Gestion_Des_Anonces.Api.Config.JwtService;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.AnonceRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Reservation.Reservation;
import PFE.Gestion_Des_Anonces.Api.Models.Reservation.ReservationRepository;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import PFE.Gestion_Des_Anonces.Api.Models.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembreService {

    @Autowired
    private final AnonceRepository anonceRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final ReservationRepository reservationRepository;

    public ResponseEntity<?> reserver(Long id , Reservation reservation) {
        Optional<Anonce> anonceOptional = anonceRepository.findById(id);
        Anonce anonce = anonceOptional.get();
        if(anonce == null){
            return ResponseEntity.badRequest().build();
        }
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = (User)principal;
            user.reserver(reservation);
            reservation.setIdMembre(user);
            reservationRepository.save(reservation);
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
