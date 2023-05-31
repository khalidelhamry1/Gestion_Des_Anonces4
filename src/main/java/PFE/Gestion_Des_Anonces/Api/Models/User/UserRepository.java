package PFE.Gestion_Des_Anonces.Api.Models.User;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Transactional
    List<User> findByEmail(String email);

}
