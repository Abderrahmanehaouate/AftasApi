package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.dtos.CompetitionDto.CompetitionRequestDto;
import ma.youcode.aftas.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public List<CompetitionRequestDto> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return competitionService.getAllCompetitions(page, size);
    }

    @GetMapping("/{id}")
    public CompetitionRequestDto getCompetitionById(@PathVariable Long id) {
        return competitionService.getCompetitionById(id);
    }

    @PostMapping("/create")
    public CompetitionRequestDto createCompetition(@RequestBody CompetitionRequestDto competitionDto) {
        return competitionService.createCompetition(competitionDto);
    }

    @PutMapping("/update")
    public CompetitionRequestDto updateCompetition(@RequestBody CompetitionRequestDto competitionDto) {
        return competitionService.updateCompetition(competitionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetitionById(@PathVariable Long id) {
        competitionService.deleteById(id);
    }

}
