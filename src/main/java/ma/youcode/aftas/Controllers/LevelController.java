package ma.youcode.aftas.Controllers;

import ma.youcode.aftas.Services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {
    private final LevelService levelService;
    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }
}
