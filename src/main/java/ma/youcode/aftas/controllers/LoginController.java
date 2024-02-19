package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.dtos.authenticationDto.AuthDto;
import ma.youcode.aftas.models.dtos.authenticationDto.LoginDto;
import ma.youcode.aftas.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}