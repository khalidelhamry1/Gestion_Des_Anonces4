package PFE.Gestion_Des_Anonces.Api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/Membre")
public class MembreController {
    @GetMapping(path = "/hello")
    public String hello(){
        return  "hello Membre !";
    }
}
