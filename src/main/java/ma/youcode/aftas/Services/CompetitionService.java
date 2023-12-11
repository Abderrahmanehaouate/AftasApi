package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.CompetitionDto.CompetitionRequestDto;
import ma.youcode.aftas.Models.Entities.Competition;
import ma.youcode.aftas.Repositories.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    public Competition createCompetition(CompetitionRequestDto competitionDto) {
        String location = competitionDto.getLocation();
        String code = generateCompetitionCode(location);
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(code);
        return competitionRepository.save(competition);
    }
    public Competition updateCompetition(CompetitionRequestDto competitionDto) {
        String location = competitionDto.getLocation();
        String code = generateCompetitionCode(location);
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(code);
        return competitionRepository.save(competition);
    }
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }

    private String generateCompetitionCode(String location) {
        String locationCode = location.substring(0, Math.min(location.length(), 3));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        String formattedDate = dateFormat.format(new Date());

        return locationCode.toLowerCase() + "-" + formattedDate;
    }
}
