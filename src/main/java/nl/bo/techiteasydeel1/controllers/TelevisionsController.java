package nl.bo.techiteasydeel1.controllers;

import jakarta.validation.Valid;
import nl.bo.techiteasydeel1.dtos.TelevisionDto;
import nl.bo.techiteasydeel1.dtos.TelevisionInputDto;
import nl.bo.techiteasydeel1.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {

        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getTelevisions() {
        List<TelevisionDto> responseDto = televisionService.getTelevision();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable Long id) {
        TelevisionDto televisionDto = televisionService.televisionById(id);

        return ResponseEntity.ok(televisionDto);
    }


    @PostMapping("/televisions")
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {

        TelevisionDto dto = televisionService.saveTelevision(televisionInputDto);

        return ResponseEntity.created(null).body(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTv(@PathVariable Long id, @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto dto = televisionService.updateTelevision(id, televisionInputDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTv(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }


}

