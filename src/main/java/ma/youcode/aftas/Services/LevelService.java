package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.LevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }
}
