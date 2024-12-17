package nl.bo.techiteasydeel1.controllers;

import nl.bo.techiteasydeel1.dtos.WallBracketDto;
import nl.bo.techiteasydeel1.dtos.WallBracketInputDto;
import nl.bo.techiteasydeel1.models.CIModule;
import nl.bo.techiteasydeel1.models.WallBracket;
import nl.bo.techiteasydeel1.repositories.CIModulesRepository;
import nl.bo.techiteasydeel1.repositories.WallBracketRepository;
import nl.bo.techiteasydeel1.services.WallBracketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wall-brackets")
public class WallBracketController {
    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getWallBrackets(){
        List<WallBracketDto> wallBracketsDto = wallBracketService.getWallBrackets();
        return ResponseEntity.ok(wallBracketsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracketById(@PathVariable Long id){
        WallBracketDto wallBracketDto = wallBracketService.getWallBracketById(id);
        return ResponseEntity.ok(wallBracketDto);
    }

    @PostMapping
    public ResponseEntity<WallBracketDto> addWallBracket(@RequestBody WallBracketInputDto wallBracketInputDto){
        WallBracketDto wallBracketDto = wallBracketService.saveWallBracket(wallBracketInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(wallBracketDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable Long id, @RequestBody WallBracketInputDto wallBracketInputDto){
        WallBracketDto wallBracketDto = wallBracketService.updateWallBracket(id, wallBracketInputDto);
            return ResponseEntity.ok(wallBracketDto);
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable Long id){
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }
}
