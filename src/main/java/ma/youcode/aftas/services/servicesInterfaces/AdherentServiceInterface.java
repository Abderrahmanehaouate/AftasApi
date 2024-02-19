package ma.youcode.aftas.services.servicesInterfaces;

import ma.youcode.aftas.models.dtos.adherentDto.AdherentRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AdherentServiceInterface {
    AdherentRequestDto createAdherent(AdherentRequestDto adherentRequestDto);
}
