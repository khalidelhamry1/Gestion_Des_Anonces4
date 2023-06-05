package PFE.Gestion_Des_Anonces.Api.Services;


import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.AnonceRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Categorie.Categorie;
import PFE.Gestion_Des_Anonces.Api.Models.Categorie.CategorieRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Ville.Ville;
import PFE.Gestion_Des_Anonces.Api.Models.Ville.VilleRepository;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_HUB;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_SEARCH;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.COMMENTAIRE_DTO;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.USER_COMMENT_DTO;
import PFE.Gestion_Des_Anonces.Api.utils.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Autowired
    private final AnonceRepository anonceRepository;

    @Autowired
    private final VilleRepository villeRepository;

    @Autowired
    private final CategorieRepository categorieRepository;
    
    public List<ANONCE_DTO_SEARCH> filterSearch(SearchFilter filter) {
        List<Anonce> anonces = anonceRepository.getWithFilterNoCategories(
                filter.getMinPrix(),
                filter.getMaxPrix(),
                filter.getChambres(),
                filter.getSalles(),
                "%"+filter.getVille()+"%"
        );
        if(filter.getCategories().length != 0){
            List<String> FilterCategories = Arrays.asList(filter.getCategories());
            List<Anonce> FilteredAnonces = new ArrayList<Anonce>();
            for(Anonce anonce : anonces){
                if(anonce.getCategories().size() >= FilterCategories.size()) {
                    int i=0;
                    int count=0;
                    while( i < anonce.getCategories().size() && count != FilterCategories.size()) {
                        if (FilterCategories.contains(anonce.getCategories().get(i).getIdCategorie()))count++;
                        i++;
                    };
                    if(count == FilterCategories.size())FilteredAnonces.add(anonce);
                }
            }
            anonces = FilteredAnonces;
        }
        List<ANONCE_DTO_SEARCH> DTOS = anonces.stream().map(anonce -> new ANONCE_DTO_SEARCH(
                anonce.getIdAnonce(),
                0,
                anonce.getPrix(),
                anonce.getLatitude(),
                anonce.getLongitude(),
                anonce.getType(),
                anonce.getEnabled(),
                anonce.getImageUrl(),
                anonce.getNomAnonce(),
                anonce.getIdVille().getIdVille(),
                anonce.getIdVille().getIdRegion().getIdRegion()
        )).toList();
        return DTOS;
    }

    public List<ANONCE_DTO_SEARCH> getAll() {
        List<Anonce> anonces = anonceRepository.findAll().stream().filter(anonce -> anonce.getEnabled()).toList();
        return anonces.stream().map(anonce -> new ANONCE_DTO_SEARCH(
                anonce.getIdAnonce(),
                0,
                anonce.getPrix(),
                anonce.getLatitude(),
                anonce.getLongitude(),
                anonce.getType(),
                anonce.getEnabled(),
                anonce.getImageUrl(),
                anonce.getNomAnonce(),
                anonce.getIdVille().getIdVille(),
                anonce.getIdVille().getIdRegion().getIdRegion()
        )).toList();
    }

    public List<String> getVilles() {
        List<Ville> villes = villeRepository.findAll();
        return villes.stream().map(ville -> ville.getIdVille()).toList();
    }

    public List<String> getCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        return  categories.stream().map(categorie -> categorie.getIdCategorie()).toList();
    }

    public ANONCE_DTO_HUB getAnonce(Long id) {
        Optional<Anonce> anonce = anonceRepository.findById(id);
        if(anonce.isEmpty()){
            return null;
        }
        Anonce A = anonce.get();
        List<COMMENTAIRE_DTO> comments = A.getCommentaires().stream().map(
                commentaire -> new COMMENTAIRE_DTO(
                        new USER_COMMENT_DTO(commentaire.getIdMembre().getNom(),commentaire.getIdMembre().getPrenom())
                        , commentaire.getDatePublication()
                        , commentaire.getContenu()
                )
        ).toList();
        return new ANONCE_DTO_HUB(
                A.getNomAnonce(),
                A.getSurface(),
                A.getNbreSalleBain(),
                A.getNbreChambres(),
                A.getNbreEtages(),
                A.getPrix(),
                A.getImageUrl(),
                A.getDescription(),
                A.getEmail(),
                A.getTelephone(),
                A.getIdVille().getIdVille(),
                A.getIdVille().getIdRegion().getIdRegion(),
                comments
        );
    }
}

