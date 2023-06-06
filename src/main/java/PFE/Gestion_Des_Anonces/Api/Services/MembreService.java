package PFE.Gestion_Des_Anonces.Api.Services;

import PFE.Gestion_Des_Anonces.Api.Config.JwtService;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.AnonceRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Reservation.Reservation;
import PFE.Gestion_Des_Anonces.Api.Models.Reservation.ReservationRepository;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import PFE.Gestion_Des_Anonces.Api.Models.User.UserRepository;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.RESERVATION_DTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    @Transactional
    public ResponseEntity<?> reserver(RESERVATION_DTO reservation) {
        Optional<Anonce> anonceOptional = anonceRepository.findById(reservation.id());
        Anonce anonce = anonceOptional.get();
        if(anonce == null){
            return ResponseEntity.badRequest().build();
        }
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = (User)principal;
            if(reservation.DateReservationDepart().isBefore(reservation.DateReservationArrive())){
                throw new Exception();
            }
            Reservation res = Reservation
                    .builder()
                    .DateReservationArrive(reservation.DateReservationArrive())
                    .DateReservationDepart(reservation.DateReservationDepart())
                    .accepted(false)
                    .nbrAdultes(reservation.nbrAdultes())
                    .nbrEnfants(reservation.nbrEnfants())
                    .emailClient(reservation.emailClient())
                    .build();
            //anonce.getReservations().add(res);
            reservationRepository.save(res);
            res.setIdAnonce(anonce);
            res.setIdMembre(user);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return ResponseEntity.badRequest().build();
        }
    }
}
