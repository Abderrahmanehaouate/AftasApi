package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.dtos.RankingDto.RankingRequestDto;
import ma.youcode.aftas.models.dtos.RankingDto.RankingResponseDto;
import ma.youcode.aftas.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/rankings")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public List<RankingRequestDto> getAllRankings() {
        return rankingService.getAllRankings();
    }

    @GetMapping("/{id}")
    public RankingRequestDto getRankingById(@PathVariable Long id) {
        return rankingService.getRankingById(id);
    }

    @PostMapping("/create")
    public RankingRequestDto createRanking(@RequestBody RankingResponseDto rankingDto) {
        return rankingService.createRanking(rankingDto);
    }

    @PutMapping("/update")
    public RankingRequestDto updateRanking(@RequestBody RankingRequestDto rankingDto) {
        return rankingService.updateRanking(rankingDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRankingById(@PathVariable Long id) {
        rankingService.deleteById(id);
    }
}
