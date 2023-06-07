package PFE.Gestion_Des_Anonces.Api.Services;

import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.AnonceRepository;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_ADMIN;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_HUB;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_SEARCH;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private final AnonceRepository anonceRepository;
    public List<Anonce> getAnonce() {
        return anonceRepository.findAll();
    }

    public List<ANONCE_DTO_ADMIN> getAll() {
        List<Anonce> anonces = anonceRepository.findAll();
        return anonces.stream().map(A -> new ANONCE_DTO_ADMIN(
                A.getIdAnonce(),
                A.getNomAnonce(),
                A.getSurface(),
                A.getNbreSalleBain(),
                A.getNbreChambres(),
                A.getNbreEtages(),
                A.getEnabled(),
                A.getPrix(),
                A.getImageUrl(),
                A.getDescription(),
                A.getEmail(),
                A.getTelephone(),
                A.getIdVille().getIdVille(),
                A.getIdVille().getIdRegion().getIdRegion(),
                null
        )).toList();
    }


    public void deleteAnonce(Long id) {
        anonceRepository.deleteById(id);
    }
}
