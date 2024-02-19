package ma.youcode.aftas.models.Dtos.CompetitionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.models.Dtos.RankingDto.RankingDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionRequestDto {
    private Long id;
    private Date date;
    private String code;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    private List<RankingDto> rankings = new ArrayList<RankingDto>();
}
