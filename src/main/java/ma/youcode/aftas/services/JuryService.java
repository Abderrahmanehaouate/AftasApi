package ma.youcode.aftas.services;

import ma.youcode.aftas.exception.ApiRequestException;
import ma.youcode.aftas.models.Entities.Jury;
import ma.youcode.aftas.models.dtos.juryDto.JuryRequestDto;
import ma.youcode.aftas.repositories.JuryRepository;
import ma.youcode.aftas.repositories.ManagerRepository;
import ma.youcode.aftas.services.servicesInterfaces.JuryServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class JuryService implements JuryServiceInterface {

    private final JuryRepository juryRepository;
    private final ManagerRepository managerRepository;
    private final ModelMapper modelMapper;

    public JuryService(JuryRepository juryRepository, ManagerRepository managerRepository, ModelMapper modelMapper) {
        this.juryRepository = juryRepository;
        this.managerRepository = managerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JuryRequestDto createJury(JuryRequestDto juryRequestDto) {
        Jury jury = modelMapper.map(juryRequestDto, Jury.class);

        if (managerRepository.existsByEmail(jury.getEmail()) ||
                juryRepository.existsByEmail(jury.getEmail())) {
            throw new ApiRequestException("Email already exists");
        }

        Jury savedJury = juryRepository.save(jury);
        return modelMapper.map(savedJury, JuryRequestDto.class);
    }
}
