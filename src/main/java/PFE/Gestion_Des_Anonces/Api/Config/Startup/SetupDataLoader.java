package PFE.Gestion_Des_Anonces.Api.Config.Startup;

import PFE.Gestion_Des_Anonces.Api.Models.Anonce.Anonce;
import PFE.Gestion_Des_Anonces.Api.Models.Anonce.AnonceRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Categorie.Categorie;
import PFE.Gestion_Des_Anonces.Api.Models.Categorie.CategorieRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Commentaire.Commentaire;
import PFE.Gestion_Des_Anonces.Api.Models.Commentaire.CommentaireRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Privilege.Privilege;
import PFE.Gestion_Des_Anonces.Api.Models.Privilege.PrivilegeRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Region.Region;
import PFE.Gestion_Des_Anonces.Api.Models.Region.RegionRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Role.Role;
import PFE.Gestion_Des_Anonces.Api.Models.Role.RoleRepository;
import PFE.Gestion_Des_Anonces.Api.Models.User.User;
import PFE.Gestion_Des_Anonces.Api.Models.User.UserRepository;
import PFE.Gestion_Des_Anonces.Api.Models.Ville.Ville;
import PFE.Gestion_Des_Anonces.Api.Models.Ville.VilleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnonceRepository anonceRepository;
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ADMIN", adminPrivileges);
        createRoleIfNotFound("Membre", Collections.singletonList(readPrivilege));

        List<Role> adminRole = roleRepository.findByName("ADMIN");
        List<Role> membreRole = roleRepository.findByName("Membre");
        User user = new User();
        user.setNom("Test");
        user.setPrenom("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(adminRole);
        user.setEnabled(true);
        userRepository.save(user);
        User user1 = new User();
        user1.setNom("Test_Membre");
        user1.setPrenom("Test_Membre");
        user1.setPassword(passwordEncoder.encode("test"));
        user1.setEmail("Test_Membre@test.com");
        user1.setRoles(membreRole);
        user1.setEnabled(true);
        userRepository.save(user1);
        List<Commentaire> comments = List.of(
                Commentaire.builder()
                .contenu("Hadchi nadi nadi bezaf!")
                .idMembre(user1)
                .DatePublication(Timestamp.valueOf(LocalDateTime.now()))
                .build(),
                Commentaire.builder()
                .contenu("Hadchi nadi nadi bezaf!")
                .idMembre(user1)
                .DatePublication(Timestamp.valueOf(LocalDateTime.now()))
                .build(),
                Commentaire.builder()
                .contenu("Hadchi nadi nadi bezaf!")
                .idMembre(user1)
                .DatePublication(Timestamp.valueOf(LocalDateTime.now()))
                .build(),
                Commentaire.builder()
                .contenu("Hadchi nadi nadi bezaf!")
                .idMembre(user1)
                .DatePublication(Timestamp.valueOf(LocalDateTime.now()))
                .build()
        );
        for(Commentaire C : comments){
            commentaireRepository.save(C);
        }
        List<Categorie> Categories = List.of(
                Categorie
                .builder()
                .idCategorie("Appartement")
                .build(),
                Categorie
                .builder()
                .idCategorie("Piscine")
                .build()
        );
        for(Categorie C : Categories){
            categorieRepository.save(C);
        }
        Region casaStat = Region
                .builder()
                .idRegion("Casablanca-settat")
                .build();
        regionRepository.save(casaStat);
        Ville casa = Ville
                .builder()
                .idVille("Casablanca")
                .idRegion(casaStat)
                .build();
        casaStat = regionRepository.findAll().get(0);
        casa.setIdRegion(casaStat);
        villeRepository.save(casa);
        Categories = categorieRepository.findAll();
        comments = commentaireRepository.findAll();
        casa = villeRepository.findAll().get(0);

        List<Anonce> anonces = List.of(
                Anonce.builder()
                        .email("email@email.com")
                        .etat('A')
                        .dateCreationAnonce(Timestamp.valueOf(LocalDateTime.now()))
                        .description("Nestled amidst breathtaking  decor, plush furnishings.")
                        .idProprietaire(user1)
                        .imageUrl("https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2")
                        .latitude((float)Math.random()*90)
                        .longitude((float)Math.random()*180)
                        .nomAnonce("Serena Hub")
                        .type('L')
                        .prix(100)
                        .telephone("0694853606")
                        .surface(400)
                        .nbreChambres(5)
                        .nbreEtages(1)
                        .nbreSalleBain(10)
                        .build(),
                Anonce.builder()
                        .email("email@email.com")
                        .etat('A')
                        .dateCreationAnonce(Timestamp.valueOf(LocalDateTime.now()))
                        .description("Nestled amidst breathtaking  decor, plush furnishings.")
                        .idProprietaire(user1)
                        .imageUrl("https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2")
                        .latitude((float)Math.random()*90)
                        .longitude((float)Math.random()*180)
                        .nomAnonce("Serena Hub")
                        .type('L')
                        .prix(100)
                        .telephone("0694853606")
                        .surface(400)
                        .nbreChambres(5)
                        .nbreEtages(1)
                        .nbreSalleBain(10)
                        .build(),
                Anonce.builder()
                        .email("email@email.com")
                        .etat('A')
                        .dateCreationAnonce(Timestamp.valueOf(LocalDateTime.now()))
                        .description("Nestled amidst breathtaking  decor, plush furnishings.")
                        .idProprietaire(user1)
                        .imageUrl("https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&w=350&dpr=2")
                        .latitude((float)Math.random()*90)
                        .longitude((float)Math.random()*180)
                        .nomAnonce("Serena Hub")
                        .type('A')
                        .prix(100)
                        .telephone("0694853606")
                        .surface(400)
                        .nbreChambres(5)
                        .nbreEtages(1)
                        .nbreSalleBain(10)
                        .build());
        for(Anonce X : anonces) {
            X.setCategories(Categories);
            X.setCommentaires(comments);
            X.setIdVille(casa);
        }
        List<Anonce> anonces1 = new ArrayList<>(anonces);
        List<Anonce> anonces2 = new ArrayList<>(anonces);
        anonceRepository.saveAll(anonces);
        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, List<Privilege> privileges) {
        List<Role> roleList = roleRepository.findByName(name);
        if (roleList.size() == 0) {
            Role role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
            roleList.add(role);
        }
        return roleList.get(0);
    }
}