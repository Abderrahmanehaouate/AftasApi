package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.FishRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public FishService(FishRepository fishRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }
}
