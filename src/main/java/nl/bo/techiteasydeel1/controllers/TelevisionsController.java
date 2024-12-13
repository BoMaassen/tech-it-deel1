package nl.bo.techiteasydeel1.controllers;

import nl.bo.techiteasydeel1.dtos.TelevisionDto;
import nl.bo.techiteasydeel1.exceptions.RecordNotFoundException;
import nl.bo.techiteasydeel1.models.Television;
import nl.bo.techiteasydeel1.repositories.TelevisionRepository;
import nl.bo.techiteasydeel1.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;
    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.televisionRepository = televisionRepository;
        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getTelevisions(){
        List<Television> responseDto = televisionService.getTelevision();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable Long id){
        Optional<TelevisionDto> televisionDto = televisionService.televisionById(id);

            return ResponseEntity.ok(televisionDto);
        }
    }

    @PostMapping
    public ResponseEntity<TelevisionDto> createTv(@RequestBody Television television){
        Television newTv = televisionRepository.save(television);
        return ResponseEntity.created(null).body(newTv);

    }
    @PutMapping ("/{id}")
    public ResponseEntity<TelevisionDto> updateTv(@PathVariable Long id, @RequestBody Television television){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTv(@PathVariable Long id){
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> updatePartialTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {

            Television television1 = television.get();
            if (newTelevision.getAmbiLight() != null) {
                television1.setAmbiLight(newTelevision.getAmbiLight());
            }
            if (newTelevision.getAvailableSize() != null) {
                television1.setAvailableSize(newTelevision.getAvailableSize());
            }
            if (newTelevision.getBluetooth()) {
                television1.setBluetooth(newTelevision.getBluetooth());
            }
            if (newTelevision.getBrand() != null) {
                television1.setBrand(newTelevision.getBrand());
            }
            if (newTelevision.getHdr() != null) {
                television1.setHdr(newTelevision.getHdr());
            }
            if (newTelevision.getName() != null) {
                television1.setName(newTelevision.getName());
            }
            if (newTelevision.getOriginalStock() != null) {
                television1.setOriginalStock(newTelevision.getOriginalStock());
            }
            if (newTelevision.getPrice() != null) {
                television1.setPrice(newTelevision.getPrice());
            }
            if (newTelevision.getRefreshRate() != null) {
                television1.setRefreshRate(newTelevision.getRefreshRate());
            }
            if (newTelevision.getScreenQuality() != null) {
                television1.setScreenQuality(newTelevision.getScreenQuality());
            }
            if (newTelevision.getScreentype() != null) {
                television1.setScreentype(newTelevision.getScreentype());
            }
            if (newTelevision.getSmartTv() != null) {
                television1.setSmartTv(newTelevision.getSmartTv());
            }
            if (newTelevision.getSold() != null) {
                television1.setSold(newTelevision.getSold());
            }
            if (newTelevision.getType() != null) {
                television1.setType(newTelevision.getType());
            }
            if (newTelevision.getVoiceControl() != null) {
                television1.setVoiceControl(newTelevision.getVoiceControl());
            }
            if (newTelevision.getWifi() != null) {
                television1.setWifi(newTelevision.getWifi());
            }

            Television returnTelevision = televisionRepository.save(television1);
            return ResponseEntity.ok().body(returnTelevision);
        }
    }


}
