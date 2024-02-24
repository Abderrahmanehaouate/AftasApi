package ma.youcode.aftas.repositories;

import ma.youcode.aftas.models.Entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    Adherent findByEmail(String username);

    boolean existsByEmail(String email);
}
