package ma.youcode.aftas.Services;

import ma.youcode.aftas.Exception.ExceptionCompetition;
import ma.youcode.aftas.Models.Dtos.CompetitionDto.CompetitionRequestDto;
import ma.youcode.aftas.Models.Entities.Competition;
import ma.youcode.aftas.Repositories.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public List<CompetitionRequestDto> getAllCompetitions(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Slice<Competition> competitions = competitionRepository.findAll(pageRequest);
        return competitions.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CompetitionRequestDto convertToDto(Competition competition) {
        return modelMapper.map(competition, CompetitionRequestDto.class);
    }

    public CompetitionRequestDto getCompetitionById(Long id) {
        Competition competition = competitionRepository.findById(id).orElse(null);
        return convertToDto(competition);
    }

    public CompetitionRequestDto createCompetition(CompetitionRequestDto competitionDto) {
        String location = competitionDto.getLocation();
        String code = generateCompetitionCode(location);
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(code);

        if (competitionRepository.existsByCode(competition.getCode())) {
            throw new ExceptionCompetition("Competition with code '" + competition.getCode() + "' already exists.");
        }

        if (competitionRepository.existsByDate(competitionDto.getDate())) {
            throw new ExceptionCompetition("Already exists a competition in the same date");
        }

        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionRequestDto.class);
    }

    public CompetitionRequestDto updateCompetition(CompetitionRequestDto competitionDto) {
        String code = generateCompetitionCode(competitionDto.getLocation());
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(code);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionRequestDto.class);
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
