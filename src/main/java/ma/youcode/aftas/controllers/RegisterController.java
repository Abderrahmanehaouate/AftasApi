package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.dtos.MemberDto.MemberRequestDto;
import ma.youcode.aftas.models.dtos.authenticationDto.AuthDto;
import ma.youcode.aftas.models.dtos.juryDto.JuryRequestDto;
import ma.youcode.aftas.models.dtos.managerDto.ManagerRequestDto;
import ma.youcode.aftas.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("member")
    public ResponseEntity<AuthDto> registerMember(@RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity.ok(authService.registerMember(memberRequestDto));
    }

    @PostMapping("jury")
    public ResponseEntity<AuthDto> registerJury(@RequestBody JuryRequestDto juryRequestDto){
        return ResponseEntity.ok(authService.registerJury(juryRequestDto));
    }

    @PostMapping("manager")
    public ResponseEntity<AuthDto> registerManager(@RequestBody ManagerRequestDto managerRequestDto){
        return ResponseEntity.ok(authService.registerManager(managerRequestDto));
    }


}
