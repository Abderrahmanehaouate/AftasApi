package ma.youcode.aftas.services.servicesInterfaces;

import ma.youcode.aftas.models.dtos.juryDto.JuryRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface JuryServiceInterface {
    JuryRequestDto createJury(JuryRequestDto juryRequestDto);
}