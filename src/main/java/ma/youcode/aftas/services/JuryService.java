package ma.youcode.aftas.services;

import ma.youcode.aftas.models.Entities.Jury;
import ma.youcode.aftas.models.dtos.juryDto.JuryRequestDto;
import ma.youcode.aftas.repositories.JuryRepository;
import ma.youcode.aftas.services.servicesInterfaces.JuryServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class JuryService implements JuryServiceInterface {

    private final JuryRepository juryRepository;
    private final ModelMapper modelMapper;

    public JuryService(JuryRepository juryRepository, ModelMapper modelMapper) {
        this.juryRepository = juryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JuryRequestDto createJury(JuryRequestDto juryRequestDto) {
        Jury jury = modelMapper.map(juryRequestDto, Jury.class);
        Jury savedJury = juryRepository.save(jury);
        return modelMapper.map(savedJury, JuryRequestDto.class);
    }
}
