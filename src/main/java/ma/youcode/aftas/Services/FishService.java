package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishService {
    private final FishRepository fishRepository;
    @Autowired
    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }
}
