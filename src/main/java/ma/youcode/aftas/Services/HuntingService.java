package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.HuntingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntingService {
    private final HuntingRepository huntingRepository;
    @Autowired
    public HuntingService(HuntingRepository huntingRepository) {
        this.huntingRepository = huntingRepository;
    }
}
