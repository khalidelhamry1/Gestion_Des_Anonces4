package PFE.Gestion_Des_Anonces.Api.Controllers;

import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_SEARCH;
import PFE.Gestion_Des_Anonces.Api.Services.SearchService;
import PFE.Gestion_Des_Anonces.Api.utils.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/api/Search")
@RequiredArgsConstructor
public class SearchController {
    @Autowired
    private final SearchService searchService;

    @GetMapping
    public List<ANONCE_DTO_SEARCH> getAll(){
        return searchService.getAll();
    }

    @PostMapping(path = "/filter")
    public List<ANONCE_DTO_SEARCH> filterSearch(@RequestBody SearchFilter filter){
        return searchService.filterSearch(filter);
    }

}
