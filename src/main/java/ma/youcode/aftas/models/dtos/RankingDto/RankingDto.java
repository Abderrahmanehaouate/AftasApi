package ma.youcode.aftas.models.dtos.RankingDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.models.dtos.MemberDto.MemberResponseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankingDto {
    private Long competitionId;
    private Long memberId;
    private Integer rank = 0;
    private Integer score = 0;
    private MemberResponseDto member;
}
