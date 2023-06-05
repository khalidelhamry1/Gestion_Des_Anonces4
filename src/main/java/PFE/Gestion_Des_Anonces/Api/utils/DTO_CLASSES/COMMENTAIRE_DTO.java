package PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES;

import java.sql.Timestamp;

public record COMMENTAIRE_DTO (
            USER_COMMENT_DTO membre,
            Timestamp datePub ,
            String contenu
){}
