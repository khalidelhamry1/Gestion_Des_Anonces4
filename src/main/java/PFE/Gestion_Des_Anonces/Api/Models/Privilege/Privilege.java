package PFE.Gestion_Des_Anonces.Api.Models.Privilege;

import PFE.Gestion_Des_Anonces.Api.Models.Role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges" ,fetch = FetchType.EAGER)
    private List<Role> roles;
}