package nl.bo.techiteasydeel1.controllers;

import jakarta.validation.Valid;
import nl.bo.techiteasydeel1.dtos.RemoteControllerDto;
import nl.bo.techiteasydeel1.dtos.RemoteControllerInputDto;
import nl.bo.techiteasydeel1.services.RemoteControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remote-controllers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }


    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getRemoteControllers() {
        List<RemoteControllerDto> responseDto = remoteControllerService.getRemoteControllers();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerById(@PathVariable Long id) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.remoteControllerById(id);
        return ResponseEntity.ok(remoteControllerDto);
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto dto = remoteControllerService.saveRemoteController(remoteControllerInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@PathVariable Long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto dto = remoteControllerService.updateRemoteController(id, remoteControllerInputDto);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }
}
