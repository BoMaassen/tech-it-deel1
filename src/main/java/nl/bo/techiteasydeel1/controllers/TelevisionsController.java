package nl.bo.techiteasydeel1.controllers;

import jakarta.validation.Valid;
import nl.bo.techiteasydeel1.dtos.IdInputDto;
import nl.bo.techiteasydeel1.dtos.TelevisionDto;
import nl.bo.techiteasydeel1.dtos.TelevisionInputDto;
import nl.bo.techiteasydeel1.services.TelevisionService;
import org.springframework.http.HttpStatus;
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


    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {

        TelevisionDto dto = televisionService.saveTelevision(televisionInputDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTv(@Valid @PathVariable Long id, @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto dto = televisionService.updateTelevision(id, televisionInputDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTv(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/remotecontroller")
    public ResponseEntity<TelevisionDto> linkRemoteController(@PathVariable Long id, @RequestBody IdInputDto input) {
        TelevisionDto dto = televisionService.assignRemoteControllerToTelevision(id, input);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/cimodule")
    public ResponseEntity<TelevisionDto> linkCiModule(@PathVariable Long id, @RequestBody IdInputDto input) {
        TelevisionDto dto = televisionService.assignCiModuleToTelevision(id, input);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/wallbracket")
    public ResponseEntity<TelevisionDto> linkWallBracket(@PathVariable Long id, @RequestBody IdInputDto input) {
        TelevisionDto dto = televisionService.assignWallBracketToTelevision(id, input);
        return ResponseEntity.ok(dto);
    }


}

