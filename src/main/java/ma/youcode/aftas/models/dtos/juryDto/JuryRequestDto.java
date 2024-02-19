package ma.youcode.aftas.models.dtos.juryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JuryRequestDto {
    private Long id;
    private String fistName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
}
