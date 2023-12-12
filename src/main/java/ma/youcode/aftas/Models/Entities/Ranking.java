package ma.youcode.aftas.Models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private Integer rank;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionId")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    @JsonBackReference
    private Member member;

}