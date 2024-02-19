package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.Dtos.MemberDto.MemberRequestDto;
import ma.youcode.aftas.models.Dtos.authenticationDto.AuthDto;
import ma.youcode.aftas.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
