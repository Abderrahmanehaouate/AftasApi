package ma.youcode.aftas.repositories;

import ma.youcode.aftas.models.Entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    List<Ranking> findAllByCompetitionId(Long competitionId);
    List<Ranking> findAllByMemberId(Long memberId);

    boolean existsByMemberIdAndCompetitionId(Long memberId, Long competitionId);

    Ranking findByMemberIdAndCompetitionId(Long memberId, Long competitionId);

}