package ma.youcode.aftas.controllers;

import ma.youcode.aftas.models.dtos.FishDto.FishRequestDto;
import ma.youcode.aftas.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fishes")
public class FishController {
    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public List<FishRequestDto> getAllFishes() {
        return fishService.getAllFishes();
    }

    @GetMapping("/{id}")
    public FishRequestDto getFishById(@PathVariable Long id) {
        return fishService.getFishById(id);
    }

    @PostMapping("/create")
    public FishRequestDto createFish(@RequestBody FishRequestDto fishDto) {
        return fishService.createFish(fishDto);
    }

    @PutMapping("/update")
    public FishRequestDto updateFish(@RequestBody FishRequestDto fishDto) {
        return fishService.updateFish(fishDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFish(@PathVariable Long id) {
        fishService.deleteFishById(id);
        return new ResponseEntity<>("Competition deleted successfully", HttpStatus.OK);
    }
}
