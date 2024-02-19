package ma.youcode.aftas.repositories;

import ma.youcode.aftas.models.Entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    boolean existsByCode(String code);

    boolean existsByDate(Date date);
}