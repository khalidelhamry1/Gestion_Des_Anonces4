package PFE.Gestion_Des_Anonces.Api.Models.Region;

import PFE.Gestion_Des_Anonces.Api.Models.Ville.Ville;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Region implements Serializable {

        @Column(name="idRegion")
        @Id
        private String idRegion;

        @OneToMany(mappedBy="idRegion")
        private List<Ville> villes;
}
