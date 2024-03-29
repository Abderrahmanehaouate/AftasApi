package ma.youcode.aftas.repositories;

import ma.youcode.aftas.models.Entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    boolean existsByMemberIdAndCompetitionIdAndFishId(Long memberId, Long competitionId, Long fishId);

    Hunting findByMemberIdAndCompetitionIdAndFishId(Long memberId, Long competitionId, Long fishId);
}