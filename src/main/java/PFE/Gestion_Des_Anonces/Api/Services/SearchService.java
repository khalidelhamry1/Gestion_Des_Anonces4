package PFE.Gestion_Des_Anonces.Api.Services;


import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.AnonceRepository;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_SEARCH;
import PFE.Gestion_Des_Anonces.Api.utils.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Autowired
    private final AnonceRepository anonceRepository;

/*
     minPrix,
     maxPrix,
     chambres,
     salles,
     ville,
     Categories
*/

    public List<ANONCE_DTO_SEARCH> filterSearch(SearchFilter filter) {
        List<Anonce> anonces;
        if(filter.getCategories() == null){
            anonces = anonceRepository.getWithFilterNoCategories(
                    filter.getMinPrix(),
                    filter.getMaxPrix(),
                    filter.getChambres(),
                    filter.getSalles(),
                    "%"+filter.getVille()+"%"
            );
        }else {
            anonces = anonceRepository.getWithFilter(
                    filter.getMinPrix(),
                    filter.getMaxPrix(),
                    filter.getChambres(),
                    filter.getSalles(),
                    "%"+filter.getVille()+"%",
                    filter.getCategories()
            );
        }
        List<ANONCE_DTO_SEARCH> DTOS = anonces.stream().map(anonce -> new ANONCE_DTO_SEARCH(
                anonce.getIdAnonce(),
                0,
                anonce.getPrix(),
                anonce.getLatitude(),
                anonce.getLongitude(),
                anonce.getType(),
                anonce.getEtat(),
                anonce.getNomAnonce(),
                anonce.getIdVille().getIdVille(),
                anonce.getIdVille().getIdRegion().getIdRegion()
        )).toList();
        return DTOS;
    }

    public List<ANONCE_DTO_SEARCH> getAll() {
        List<Anonce> anonces = anonceRepository.findAll();
        return anonces.stream().map(anonce -> new ANONCE_DTO_SEARCH(
                anonce.getIdAnonce(),
                0,
                anonce.getPrix(),
                anonce.getLatitude(),
                anonce.getLongitude(),
                anonce.getType(),
                anonce.getEtat(),
                anonce.getNomAnonce(),
                anonce.getIdVille().getIdVille(),
                anonce.getIdVille().getIdRegion().getIdRegion()
        )).toList();
    }
}
