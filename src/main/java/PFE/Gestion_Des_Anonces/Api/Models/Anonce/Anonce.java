package PFE.Gestion_Des_Anonces.Api.Models.Anonce;

import PFE.Gestion_Des_Anonces.Api.Models.Categorie.Categorie;
import PFE.Gestion_Des_Anonces.Api.Models.Commentaire.Commentaire;
import PFE.Gestion_Des_Anonces.Api.Models.Reservation.Reservation;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import PFE.Gestion_Des_Anonces.Api.Models.Ville.Ville;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Anonce")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anonce implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnonce;
    private int surface , nbreSalleBain , nbreEtages , nbreChambres;
    private float prix, latitude , longitude ;
    private char type ;
    private Boolean enabled;
    private Timestamp dateCreationAnonce;
    private String email;
    private String telephone;
    private String nomAnonce;
    private String description;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "idVille")
    private Ville idVille;

    @ManyToOne
    @JoinColumn(name = "idProprietaire")
    private User idProprietaire;

    @ManyToMany
    @JoinTable(
            name = "Categories_Anonces",
            joinColumns = @JoinColumn(name = "idAnonce"),
            inverseJoinColumns = @JoinColumn(name = "idCategorie")
    )
    private List<Categorie> categories=new ArrayList<>();
    @OneToMany(mappedBy="idAnonce")
    private List<Reservation> Reservations=new ArrayList<>();
    @OneToMany(mappedBy="idAnonce")
    private List<Commentaire> commentaires=new ArrayList<>();

}
