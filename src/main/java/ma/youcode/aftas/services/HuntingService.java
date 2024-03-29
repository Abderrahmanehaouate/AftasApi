package ma.youcode.aftas.services;

import ma.youcode.aftas.models.dtos.HuntingDto.HuntingRequestDto;
import ma.youcode.aftas.models.Entities.Hunting;
import ma.youcode.aftas.models.Entities.Ranking;
import ma.youcode.aftas.repositories.CompetitionRepository;
import ma.youcode.aftas.repositories.HuntingRepository;
import ma.youcode.aftas.repositories.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuntingService {

    private final HuntingRepository huntingRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public HuntingService(HuntingRepository huntingRepository,
                          RankingRepository rankingRepository,
                          ModelMapper modelMapper,
                          CompetitionRepository competitionRepository
    ) {
        this.huntingRepository = huntingRepository;
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
        this.competitionRepository = competitionRepository;
    }

    public List<HuntingRequestDto> getAllHuntings() {
        List<Hunting> huntings = huntingRepository.findAll();
        return huntings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HuntingRequestDto convertToDto(Hunting hunting) {
        return modelMapper.map(hunting, HuntingRequestDto.class);
    }

    public HuntingRequestDto getHuntingById(Long id) {
        Hunting hunting = huntingRepository.findById(id).orElse(null);
        return modelMapper.map(hunting, HuntingRequestDto.class);
    }

    public HuntingRequestDto createHunting(HuntingRequestDto huntingDto) {
        Long fishId = huntingDto.getFish().getId();
        Long memberId = huntingDto.getMember().getId();
        Long competitionId = huntingDto.getCompetition().getId();
        float averageWeightDto = huntingDto.getFish().getAverageWeight();

        if (rankingRepository.existsByMemberIdAndCompetitionId(memberId, competitionId)) {
//            Ranking ranking = rankingRepository.findByMemberIdAndCompetitionId(memberId, competitionId);
//            if(ranking.getCompetition().getStartDate().after(huntingDto.getHuntingDate())) {
//                throw new IllegalStateException("The competition has not started yet.");
//            }
//            if(ranking.getCompetition().getEndDate().before(huntingDto.getHuntingDate())) {
//                throw new IllegalStateException("The competition has ended.");
//            }
        } else {
            throw new IllegalStateException("The member is not registered in the competition.");
        }


        if (huntingRepository.existsByMemberIdAndCompetitionIdAndFishId(memberId, competitionId, fishId)) {
            Hunting existingHunting = huntingRepository.findByMemberIdAndCompetitionIdAndFishId(memberId, competitionId, fishId);

            int existingNumberOfFish = existingHunting.getNumberOfFish();

            int newNumberOfFish = huntingDto.getNumberOfFish();

            int totalNumberOfFish = existingNumberOfFish + newNumberOfFish;

            existingHunting.setNumberOfFish(totalNumberOfFish);

            Hunting savedHunting = huntingRepository.save(existingHunting);

            float averageWeight = savedHunting.getFish().getAverageWeight();
            if (averageWeightDto < averageWeight) {
                throw new IllegalStateException("The average weight of the fish is less than the average weight of the fish in the database.");
            }


            int score = newNumberOfFish * savedHunting.getFish().getLevel().getPoints();
            updateRanking(memberId, competitionId, score);

            return modelMapper.map(savedHunting, HuntingRequestDto.class);
        } else {
            Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
            Hunting savedHunting = huntingRepository.save(hunting);
            int score = savedHunting.getNumberOfFish() * savedHunting.getFish().getLevel().getPoints();
            System.out.println("score: " + score);
            updateRanking(memberId, competitionId, score);
            return modelMapper.map(savedHunting, HuntingRequestDto.class);
        }
    }

    private void updateRanking(Long memberId, Long competitionId, int score) {
        if (rankingRepository.existsByMemberIdAndCompetitionId(memberId, competitionId)) {
            Ranking ranking = rankingRepository.findByMemberIdAndCompetitionId(memberId, competitionId);
            System.out.println("ranking: " + ranking);
            int newScore = ranking.getScore() + score;
            System.out.println("newScore: " + newScore);
            ranking.setScore(newScore);
            rankingRepository.save(ranking);
            updateRank(competitionId);
        } else {
            throw new IllegalStateException("The member is not registered in the competition.");
        }
    }

    private void updateRank(Long competitionId) {
        // get all rankings by competitionId
        List<Ranking> rankings = rankingRepository.findAllByCompetitionId(competitionId);
        rankings.forEach(ranking -> {
            int score = ranking.getScore();
            int rank = 1;
            for (Ranking r : rankings) {
                if (r.getScore() > score) {
                    rank++;
                }
            }
            ranking.setRank(rank);
            rankingRepository.save(ranking);
        });
    }

    public HuntingRequestDto updateHunting(HuntingRequestDto huntingDto) {
        Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
        return modelMapper.map(huntingRepository.save(hunting), HuntingRequestDto.class);
    }

    public void deleteById(Long id) {
        huntingRepository.deleteById(id);
    }
}