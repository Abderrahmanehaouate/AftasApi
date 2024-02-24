package ma.youcode.aftas.services;

import ma.youcode.aftas.exception.ApiRequestException;
import ma.youcode.aftas.models.Entities.Adherent;
import ma.youcode.aftas.models.dtos.adherentDto.AdherentRequestDto;
import ma.youcode.aftas.repositories.AdherentRepository;
import ma.youcode.aftas.repositories.JuryRepository;
import ma.youcode.aftas.repositories.ManagerRepository;
import ma.youcode.aftas.services.servicesInterfaces.AdherentServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdherentService implements AdherentServiceInterface {

    private final AdherentRepository adherentRepository;
    private final JuryRepository juryRepository;
    private final ManagerRepository managerRepository;
    private final ModelMapper modelMapper;

    public AdherentService(AdherentRepository adherentRepository, JuryRepository juryRepository, ManagerRepository managerRepository, ModelMapper modelMapper) {
        this.adherentRepository = adherentRepository;
        this.juryRepository = juryRepository;
        this.managerRepository = managerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdherentRequestDto createAdherent(AdherentRequestDto adherentRequestDto) {
        Adherent adherent = modelMapper.map(adherentRequestDto, Adherent.class);

        if (managerRepository.existsByEmail(adherent.getEmail()) ||
                adherentRepository.existsByEmail(adherent.getEmail()) ||
                juryRepository.existsByEmail(adherent.getEmail())) {
            throw new ApiRequestException("Email already exists");
        }

        Adherent savedAdherent = adherentRepository.save(adherent);
        return modelMapper.map(savedAdherent, AdherentRequestDto.class);
    }
}
