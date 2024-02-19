package ma.youcode.aftas.services;

import ma.youcode.aftas.models.dtos.MemberDto.MemberRequestDto;
import ma.youcode.aftas.models.Entities.Member;
import ma.youcode.aftas.repositories.MemberRepository;
import ma.youcode.aftas.services.servicesInterfaces.MemberServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MemberRequestDto> getAllMembers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Member> members = memberRepository.findAll(pageRequest);
        return members.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MemberRequestDto convertToDto(Member member) {
        return modelMapper.map(member, MemberRequestDto.class);
    }

    @Override
    public MemberRequestDto getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        return modelMapper.map(member, MemberRequestDto.class);
    }

    @Override
    public MemberRequestDto createMember(MemberRequestDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        Member savedMember = memberRepository.save(member);
        return modelMapper.map(savedMember, MemberRequestDto.class);
    }

    @Override
    public MemberRequestDto updateMember(MemberRequestDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        Member savedMember = memberRepository.save(member);
        return modelMapper.map(savedMember, MemberRequestDto.class);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Integer CountAllMembers() {
        List<Member> members = memberRepository.findAll();
        Integer count = members.size();
        return count;
    }
}
