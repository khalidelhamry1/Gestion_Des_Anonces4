package PFE.Gestion_Des_Anonces.Api.Models.Reservation;

import java.io.Serializable;
import java.sql.Timestamp;

import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation implements Serializable {
    @Id
    private long idReservation;
    @ManyToOne
    @JoinColumn(name = "idAnonce")
    private Anonce idAnonce;

    @ManyToOne
    @JoinColumn(name = "idMembre")
    private User idMembre;

    private Timestamp DateReservationArrive;
    private Timestamp DateReservationDepart;
}