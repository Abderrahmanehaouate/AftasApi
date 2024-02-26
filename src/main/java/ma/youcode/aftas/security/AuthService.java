package ma.youcode.aftas.security;

import ma.youcode.aftas.models.Entities.Jury;
import ma.youcode.aftas.models.dtos.MemberDto.MemberRequestDto;
import ma.youcode.aftas.models.dtos.authenticationDto.AuthDto;
import ma.youcode.aftas.models.dtos.authenticationDto.LoginDto;
import ma.youcode.aftas.models.Entities.Manager;
import ma.youcode.aftas.models.Entities.Member;
import ma.youcode.aftas.models.dtos.juryDto.JuryRequestDto;
import ma.youcode.aftas.models.dtos.managerDto.ManagerRequestDto;
import ma.youcode.aftas.services.servicesInterfaces.JuryServiceInterface;
import ma.youcode.aftas.services.servicesInterfaces.ManagerServiceInterface;
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
    private final ManagerServiceInterface managerService;
    private final JuryServiceInterface juryService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(MemberServiceInterface memberService, ManagerServiceInterface managerService, JuryServiceInterface juryService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.managerService = managerService;
        this.juryService = juryService;
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

    public AuthDto registerManager(ManagerRequestDto registerRequest){

        Manager manager = modelMapper.map(registerRequest, Manager.class);
        manager.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        ManagerRequestDto managerRequestDto = modelMapper.map(manager, ManagerRequestDto.class);

        managerService.createManager(managerRequestDto);

        String jwtToken = jwtService.generateToken(manager);

        AuthDto authDto = new AuthDto();
        authDto.setToken(jwtToken);

        return authDto;
    }

    public AuthDto registerJury(JuryRequestDto registerRequest) {

        Jury jury = modelMapper.map(registerRequest, Jury.class);
        jury.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        JuryRequestDto juryRequestDto = modelMapper.map(jury, JuryRequestDto.class);

        juryService.createJury(juryRequestDto);

        String jwtToken = jwtService.generateToken(jury);

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
