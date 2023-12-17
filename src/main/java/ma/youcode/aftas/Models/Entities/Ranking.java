package ma.youcode.aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_rankings")
public class Ranking {
    @EmbeddedId
    private CompetitionMemberId id;
    @Min(value = 0, message = "Rank must be greater than 0")
    private Integer rank;
    @Min(value = 0, message = "Score must be greater than 0")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionId")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    private Member member;

}