package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.FishDto.FishRequestDto;
import ma.youcode.aftas.Models.Entities.Fish;
import ma.youcode.aftas.Repositories.FishRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public FishService(FishRepository fishRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }

    public List<Fish> getAllFishes() {
        return fishRepository.findAll();
    }

    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElse(null);
    }

    public Fish createFish(FishRequestDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);
        return fishRepository.save(fish);
    }

    public Fish updateFish(FishRequestDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);
        return fishRepository.save(fish);
    }

    public void deleteFishById(Long id) {
        fishRepository.deleteById(id);
    }
}
