package ma.youcode.aftas.Repositories;

import ma.youcode.aftas.Models.Entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}