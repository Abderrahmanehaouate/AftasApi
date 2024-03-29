package ma.youcode.aftas.models.dtos.FishDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishResponseDto {
    private Long id;
    private String name;
    private float averageWeight;
}
