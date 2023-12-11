package ma.youcode.aftas.Controllers;

import ma.youcode.aftas.Services.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/huntings")
public class HuntingController {
    private final HuntingService huntingService;
    @Autowired
    public HuntingController(HuntingService huntingService){
        this.huntingService = huntingService;
    }
}
