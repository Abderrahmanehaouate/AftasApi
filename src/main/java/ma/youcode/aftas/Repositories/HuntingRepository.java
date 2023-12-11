package ma.youcode.aftas.Repositories;

import ma.youcode.aftas.Models.Entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

}
