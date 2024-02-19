package ma.youcode.aftas.services;

import ma.youcode.aftas.models.Entities.Manager;
import ma.youcode.aftas.models.dtos.managerDto.ManagerRequestDto;
import ma.youcode.aftas.repositories.ManagerRepository;
import ma.youcode.aftas.services.servicesInterfaces.ManagerServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ManagerService implements ManagerServiceInterface {

    private final ManagerRepository managerRepository;
    private final ModelMapper modelMapper;

    public ManagerService(ManagerRepository managerRepository, ModelMapper modelMapper) {
        this.managerRepository = managerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ManagerRequestDto createManager(ManagerRequestDto managerRequestDto) {
        Manager manager = modelMapper.map(managerRequestDto, Manager.class);
        Manager savedManager = managerRepository.save(manager);
        return modelMapper.map(savedManager, ManagerRequestDto.class);
    }
}
