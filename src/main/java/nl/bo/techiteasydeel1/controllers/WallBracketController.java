package nl.bo.techiteasydeel1.controllers;

import nl.bo.techiteasydeel1.models.CIModule;
import nl.bo.techiteasydeel1.models.WallBracket;
import nl.bo.techiteasydeel1.repositories.CIModulesRepository;
import nl.bo.techiteasydeel1.repositories.WallBracketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wall-brackets")
public class WallBracketController {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketController(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }


    @GetMapping
    public ResponseEntity<List<WallBracket>> getWallBrackets(){
        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        return ResponseEntity.ok(wallBrackets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracket> getWallBracketById(@PathVariable Long id){
        WallBracket wallBracket = wallBracketRepository.getById(id);
        return ResponseEntity.ok(wallBracket);
    }

    @PostMapping
    public ResponseEntity<WallBracket> addWallBracket(@RequestBody WallBracket wallBracket){
        WallBracket savedWallBracket = wallBracketRepository.save(wallBracket);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallBracket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracket> updateWallBracket(@PathVariable Long id, @RequestBody WallBracket wallBracket){
        var currentWallBracket = wallBracketRepository.findById(id);
        if (currentWallBracket.isEmpty()){
            return ResponseEntity.notFound().build();

        }else {
            WallBracket updatedWallBracket = currentWallBracket.get();
            updatedWallBracket.setSize(wallBracket.getSize());
            updatedWallBracket.setAdjustable(wallBracket.getAdjustable());
            updatedWallBracket.setName(wallBracket.getName());
            updatedWallBracket.setPrice(wallBracket.getPrice());

            return ResponseEntity.ok(updatedWallBracket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WallBracket> deleteWallBracket(@PathVariable Long id){
        wallBracketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
