package ma.youcode.aftas.security;

import ma.youcode.aftas.models.Dtos.MemberDto.MemberRequestDto;
import ma.youcode.aftas.models.Dtos.authenticationDto.AuthDto;
import ma.youcode.aftas.models.Dtos.authenticationDto.LoginDto;
import ma.youcode.aftas.models.Entities.Member;
import ma.youcode.aftas.repositories.MemberRepository;
import ma.youcode.aftas.services.servicesInterfaces.MemberServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberServiceInterface memberService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(MemberServiceInterface memberService, MemberRepository memberRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthDto registerMember(MemberRequestDto registerRequest){

        Member member = modelMapper.map(registerRequest, Member.class);
        member.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        MemberRequestDto memberRequestDto = modelMapper.map(member, MemberRequestDto.class);

        memberService.createMember(memberRequestDto);

        String jwtToken = jwtService.generateToken(member);

        AuthDto authDto = new AuthDto();
        authDto.setToken(jwtToken);

        return authDto;
    }

    public AuthDto login(LoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtService.generateToken(userDetails);

        AuthDto authDto = new AuthDto();
        authDto.setToken(jwtToken);

        return authDto;
    }
}
