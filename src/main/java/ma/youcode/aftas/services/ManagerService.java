package ma.youcode.aftas.services;

import ma.youcode.aftas.exception.ApiRequestException;
import ma.youcode.aftas.models.Entities.Manager;
import ma.youcode.aftas.models.dtos.managerDto.ManagerRequestDto;
import ma.youcode.aftas.repositories.AdherentRepository;
import ma.youcode.aftas.repositories.JuryRepository;
import ma.youcode.aftas.repositories.ManagerRepository;
import ma.youcode.aftas.services.servicesInterfaces.ManagerServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ManagerService implements ManagerServiceInterface {

    private final ManagerRepository managerRepository;
    private final JuryRepository juryRepository;
    private final AdherentRepository adherentRepository;
    private final ModelMapper modelMapper;

    public ManagerService(ManagerRepository managerRepository, JuryRepository juryRepository, AdherentRepository adherentRepository, ModelMapper modelMapper) {
        this.managerRepository = managerRepository;
        this.juryRepository = juryRepository;
        this.adherentRepository = adherentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ManagerRequestDto createManager(ManagerRequestDto managerRequestDto) {
        Manager manager = modelMapper.map(managerRequestDto, Manager.class);

        if (managerRepository.existsByEmail(manager.getEmail()) ||
                adherentRepository.existsByEmail(manager.getEmail()) ||
                juryRepository.existsByEmail(manager.getEmail())) {
            throw new ApiRequestException("Email already exists");
        }


        Manager savedManager = managerRepository.save(manager);
        return modelMapper.map(savedManager, ManagerRequestDto.class);
    }
}
