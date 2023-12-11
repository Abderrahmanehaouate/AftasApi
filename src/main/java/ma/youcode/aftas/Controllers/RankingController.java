package ma.youcode.aftas.Controllers;

import ma.youcode.aftas.Services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController {
    private final RankingService rankingService;
    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }
}
