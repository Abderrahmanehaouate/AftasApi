package ma.youcode.aftas.services;

import ma.youcode.aftas.models.Entities.Adherent;
import ma.youcode.aftas.models.dtos.adherentDto.AdherentRequestDto;
import ma.youcode.aftas.repositories.AdherentRepository;
import ma.youcode.aftas.services.servicesInterfaces.AdherentServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdherentService implements AdherentServiceInterface {

    private final AdherentRepository adherentRepository;
    private final ModelMapper modelMapper;

    public AdherentService(AdherentRepository adherentRepository, ModelMapper modelMapper) {
        this.adherentRepository = adherentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdherentRequestDto createAdherent(AdherentRequestDto adherentRequestDto) {
        Adherent adherent = modelMapper.map(adherentRequestDto, Adherent.class);
        Adherent savedAdherent = adherentRepository.save(adherent);
        return modelMapper.map(savedAdherent, AdherentRequestDto.class);
    }
}
