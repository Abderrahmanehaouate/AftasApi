package ma.youcode.aftas.services.servicesInterfaces;

import ma.youcode.aftas.models.dtos.MemberDto.MemberRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberServiceInterface {

    List<MemberRequestDto> getAllMembers(int page, int size);
    MemberRequestDto getMemberById(Long id);
    MemberRequestDto createMember(MemberRequestDto memberDto);
    MemberRequestDto updateMember(MemberRequestDto memberDto);
    void deleteById(Long id);
    Integer CountAllMembers();
}
