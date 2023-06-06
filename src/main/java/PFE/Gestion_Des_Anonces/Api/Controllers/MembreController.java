package PFE.Gestion_Des_Anonces.Api.Controllers;

import PFE.Gestion_Des_Anonces.Api.Models.Reservation.Reservation;
import PFE.Gestion_Des_Anonces.Api.Services.MembreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/Membre")
@RequiredArgsConstructor
public class MembreController {

    @Autowired
    private final MembreService membreService;
    @PostMapping("/Reserver")
    public ResponseEntity<?> reserver(@RequestParam Long id , @RequestBody Reservation reservation*/){
        System.out.println(id+" : id");
        System.out.println(reservation.getDateReservationArrive()+" "+reservation.getDateReservationDepart());
        return membreService.reserver(id,reservation);
    }
}
