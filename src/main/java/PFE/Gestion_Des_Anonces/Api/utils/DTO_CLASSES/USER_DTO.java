package PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;


import java.sql.Timestamp;
import java.util.List;


public record USER_DTO (
     String firstName,
     String lastName,
     String email,
     Character sexe,
     Timestamp dateNaissance,
     Timestamp dateCreationCompte,
     List<Anonce> anonces
){

}
