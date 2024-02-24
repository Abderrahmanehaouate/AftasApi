package ma.youcode.aftas.repositories;

import ma.youcode.aftas.models.Entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String username);

    boolean existsByEmail(String email);
}
