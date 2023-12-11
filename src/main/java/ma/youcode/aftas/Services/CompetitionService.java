package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

}
