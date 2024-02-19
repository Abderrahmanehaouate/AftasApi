package ma.youcode.aftas.services.servicesInterfaces;

import ma.youcode.aftas.models.dtos.managerDto.ManagerRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ManagerServiceInterface {
    ManagerRequestDto createManager(ManagerRequestDto managerRequestDto);
}
