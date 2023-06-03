package PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES;



public record ANONCE_DTO_SEARCH(
         long idAnonce,
         int nbreEtoiles,
         float prix,float latitude ,float longitude ,
         char type , char etat,
         String imageUrl,
         String nomAnonce,
         String idVille ,
         String idRegion
){
}
