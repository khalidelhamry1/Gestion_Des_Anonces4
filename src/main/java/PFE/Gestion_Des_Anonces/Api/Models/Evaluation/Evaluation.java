package PFE.Gestion_Des_Anonces.Api.Models.Evaluation;

import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Evaluation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEvaluation;

    private Integer nbretoiles;

    private String contenu;

    private Timestamp datePublication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProprietaire")
    private User idProprietaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMembre")
    private User idMembre;
}

