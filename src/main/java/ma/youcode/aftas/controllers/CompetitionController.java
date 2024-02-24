package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.dtos.CompetitionDto.CompetitionRequestDto;
import ma.youcode.aftas.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('MANAGER')")
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
    @PreAuthorize("hasRole('MANAGER')")
    public CompetitionRequestDto createCompetition(@RequestBody CompetitionRequestDto competitionDto) {
        return competitionService.createCompetition(competitionDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('MANAGER')")
    public CompetitionRequestDto updateCompetition(@RequestBody CompetitionRequestDto competitionDto) {
        return competitionService.updateCompetition(competitionDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteCompetitionById(@PathVariable Long id) {
        competitionService.deleteById(id);
    }

}
