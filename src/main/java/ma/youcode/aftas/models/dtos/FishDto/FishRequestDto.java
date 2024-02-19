package ma.youcode.aftas.models.dtos.FishDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.models.dtos.HuntingDto.HuntingResponseDto;
import ma.youcode.aftas.models.dtos.LevelDto.LevelResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishRequestDto {
    private Long id;
    private String name;
    private float averageWeight;

    private List<HuntingResponseDto> huntings = new ArrayList<HuntingResponseDto>();
    private LevelResponseDto level;
}
