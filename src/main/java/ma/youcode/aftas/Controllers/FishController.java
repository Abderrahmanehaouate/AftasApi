package ma.youcode.aftas.Controllers;

import ma.youcode.aftas.Services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/fishes")
public class FishController {
    private final FishService fishService;
    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
}
