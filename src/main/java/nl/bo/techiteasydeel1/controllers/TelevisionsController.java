package nl.bo.techiteasydeel1.controllers;

import nl.bo.techiteasydeel1.models.Television;
import nl.bo.techiteasydeel1.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Television>> getTelevisions(){
        List<Television> televisions = televisionRepository.findAll();
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable Long id){
        var television = televisionRepository.findById(id);
        return television.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Television> createTv(@RequestBody Television television){
        Television newTv = televisionRepository.save(television);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTv);

    }
    @PutMapping ("/{id}")
    public ResponseEntity<Television> updateTv(@PathVariable Long id, @RequestBody Television television){
        var currentTv = televisionRepository.findById(id);
        if(currentTv.isPresent()){
            Television updatedTv = currentTv.get();
            updatedTv.setType(television.getType());
            updatedTv.setBrand(television.getBrand());
            updatedTv.setName(television.getName());
            updatedTv.setPrice(television.getPrice());
            updatedTv.setAvailableSize(television.getAvailableSize());
            updatedTv.setRefreshRate(television.getRefreshRate());
            updatedTv.setScreentype(television.getScreentype());
            updatedTv.setScreenQuality(television.getScreenQuality());
            updatedTv.setSmartTv(television.getSmartTv());
            updatedTv.setWifi(television.getWifi());
            updatedTv.setVoiceControl(television.getVoiceControl());
            updatedTv.setHdr(television.getHdr());
            updatedTv.setBluetooth(television.getBluetooth());
            updatedTv.setAmbiLight(television.getAmbiLight());
            updatedTv.setOriginalStock(television.getOriginalStock());
            updatedTv.setSold(television.getSold());
            televisionRepository.save(updatedTv);
            return ResponseEntity.ok(updatedTv);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTv(@PathVariable Long id){
        var currentTv = televisionRepository.findById(id);
        currentTv.ifPresent(televisionRepository::delete);
        return ResponseEntity.noContent().build();
    }


}
