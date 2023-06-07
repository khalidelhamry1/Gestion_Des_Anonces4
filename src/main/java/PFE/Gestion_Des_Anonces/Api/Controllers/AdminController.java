package PFE.Gestion_Des_Anonces.Api.Controllers;

import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Services.AdminService;
import PFE.Gestion_Des_Anonces.Api.Services.MembreService;
import PFE.Gestion_Des_Anonces.Api.utils.DTO_CLASSES.ANONCE_DTO_ADMIN;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminService adminService;
    @GetMapping(path = "/hello")
    public String hello(){
        return  "hello admin !";
    }
    /*@GetMapping (path = "/Anonce")
    public List<Anonce> getAnonce(){
     return  adminService.getAnonce();
    }*/
    @GetMapping
    public List<ANONCE_DTO_ADMIN> getAll(){
        return adminService.getAll();
    }
    @DeleteMapping("{idAnonce}")
    public void deleteAnonce(@PathVariable("idAnonce") Long id){
        adminService.deleteAnonce(id);
    }
    @PutMapping("{idAnonce}")
    public void deleteAjnonce(@PathVariable("idAnonce") Long id){
        adminService.deleteAnonce(id);
    }

}
