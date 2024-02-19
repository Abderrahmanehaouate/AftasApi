package ma.youcode.aftas.models.Dtos.HuntingDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.models.Dtos.CompetitionDto.CompetitionResponseDto;
import ma.youcode.aftas.models.Dtos.FishDto.FishResponseDto;
import ma.youcode.aftas.models.Dtos.MemberDto.MemberResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HuntingRequestDto {
    private Long id;
    private Integer numberOfFish;
    private float averageWeight;

    private MemberResponseDto member;
    private CompetitionResponseDto competition;
    private FishResponseDto fish;
}
