package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.HuntingDto.HuntingRequestDto;
import ma.youcode.aftas.Models.Entities.Hunting;
import ma.youcode.aftas.Repositories.HuntingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuntingService {
    private final HuntingRepository huntingRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public HuntingService(HuntingRepository huntingRepository, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.modelMapper = modelMapper;
    }

    public List<HuntingRequestDto> getAllHuntings() {
        List<Hunting> huntings = huntingRepository.findAll();
        return huntings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private HuntingRequestDto convertToDto(Hunting hunting) {
        return modelMapper.map(hunting, HuntingRequestDto.class);
    }

    public HuntingRequestDto getHuntingById(Long id) {
        Hunting hunting = huntingRepository.findById(id).orElse(null);
        return modelMapper.map(hunting, HuntingRequestDto.class);
    }

    public HuntingRequestDto createHunting(HuntingRequestDto huntingDto) {
        Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
        Hunting savedHunting = huntingRepository.save(hunting);
        return modelMapper.map(savedHunting, HuntingRequestDto.class);
    }
    public HuntingRequestDto updateHunting(HuntingRequestDto huntingDto) {
        Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
        return modelMapper.map(huntingRepository.save(hunting), HuntingRequestDto.class);
    }
    public void deleteById(Long id) {
        huntingRepository.deleteById(id);
    }
}
