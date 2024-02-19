package ma.youcode.aftas.models.dtos.managerDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerRequestDto {
    private Long id;
    private String fistName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
}
