package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingService {
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public RankingService(RankingRepository rankingRepository, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }
}
