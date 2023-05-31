package PFE.Gestion_Des_Anonces.Api.Models.Categorie;

import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Categorie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categorie implements Serializable {
    @Id
    private String idCategorie;

    @JsonIgnore
    @ManyToMany(mappedBy="categories")
    private List<Anonce> Anonces;

}

