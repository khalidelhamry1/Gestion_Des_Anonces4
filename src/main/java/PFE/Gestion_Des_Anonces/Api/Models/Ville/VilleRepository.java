package PFE.Gestion_Des_Anonces.Api.Models.Ville;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville,String> {
}
