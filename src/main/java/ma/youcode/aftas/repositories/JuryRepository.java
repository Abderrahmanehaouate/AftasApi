package ma.youcode.aftas.repositories;

import ma.youcode.aftas.models.Entities.Jury;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuryRepository extends JpaRepository<Jury, Long> {
    Jury findByEmail(String username);

    boolean existsByEmail(String email);
}
