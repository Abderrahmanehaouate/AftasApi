package ma.youcode.aftas.models.dtos.LevelDto;

import lombok.*;
import ma.youcode.aftas.models.dtos.FishDto.FishResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequestDto {
    private Long id;
    private Integer code;
    private String description;
    private Integer points;

    private List<FishResponseDto> fishes = new ArrayList<FishResponseDto>();
}
