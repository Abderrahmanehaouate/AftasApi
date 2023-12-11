package ma.youcode.aftas.Services;

import ma.youcode.aftas.Repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper){
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }
}
