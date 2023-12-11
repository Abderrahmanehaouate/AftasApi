package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.HuntingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntingService {
    private final HuntingRepository huntingRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public HuntingService(HuntingRepository huntingRepository, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.modelMapper = modelMapper;
    }
}
