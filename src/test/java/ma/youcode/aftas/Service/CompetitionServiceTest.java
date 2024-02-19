package ma.youcode.aftas.Service;

import ma.youcode.aftas.exception.ExceptionCompetition;
import ma.youcode.aftas.models.Dtos.CompetitionDto.CompetitionRequestDto;
import ma.youcode.aftas.models.Entities.Competition;
import ma.youcode.aftas.repositories.CompetitionRepository;
import ma.youcode.aftas.services.CompetitionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompetitionService competitionService;

    @Test
    public void testGetCompetitionById() {
        // Arrange
        Long competitionId = 1L;
        Competition competition = new Competition();
        competition.setId(competitionId);

        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));

        when(competition).thenReturn(new Competition());

        // Act
        CompetitionRequestDto result = competitionService.getCompetitionById(competitionId);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(competitionId, result.getId(), "Competition ID does not match");

        // Verify that modelMapper.map was called with the correct arguments
        verify(modelMapper, times(1)).map(eq(competition), eq(CompetitionRequestDto.class));
    }



    @Test
    public void testCreateCompetition() {
        // Arrange
        CompetitionRequestDto competitionDto = new CompetitionRequestDto();
        competitionDto.setDate(new Date());
        competitionDto.setLocation("TestLocation");

        Competition competition = new Competition();
        when(modelMapper.map(eq(competitionDto), eq(Competition.class))).thenReturn(competition);
        when(competitionRepository.existsByCode(anyString())).thenReturn(false);
        when(competitionRepository.existsByDate(any(Date.class))).thenReturn(false);
        when(competitionRepository.save(competition)).thenReturn(competition);

        // Act
        CompetitionRequestDto result = competitionService.createCompetition(competitionDto);

        // Assert
        assertNotNull(result, "Result should not be null");

        // Verify that modelMapper.map was called with the correct arguments
        verify(modelMapper, times(1)).map(eq(competitionDto), eq(Competition.class));

        // Verify that competitionRepository methods were called
        verify(competitionRepository, times(1)).existsByCode(anyString());
        verify(competitionRepository, times(1)).existsByDate(any(Date.class));
        verify(competitionRepository, times(1)).save(competition);
    }


    private CompetitionRequestDto convertToDto(Competition competition) {
        return modelMapper.map(competition, CompetitionRequestDto.class);
    }

    @Test
    public void testCreateCompetitionWithExistingCode() {
        // Arrange
        CompetitionRequestDto competitionDto = new CompetitionRequestDto();
        competitionDto.setDate(new Date());
        competitionDto.setLocation("TestLocation");

        Competition competition = new Competition();
        when(modelMapper.map(competitionDto, Competition.class)).thenReturn(competition);
        when(competitionRepository.existsByCode(anyString())).thenReturn(true);

        assertThrows(ExceptionCompetition.class, () -> competitionService.createCompetition(competitionDto));
        verify(competitionRepository, never()).save(competition);
    }

}