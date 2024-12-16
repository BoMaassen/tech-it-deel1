package nl.bo.techiteasydeel1.controllers;

import nl.bo.techiteasydeel1.models.RemoteController;
import nl.bo.techiteasydeel1.repositories.RemoteControllerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/remote-controllers")
public class RemoteControllerController {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerController(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    @GetMapping
    public ResponseEntity<List<RemoteController>> getRemoteControllers(){
       List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        return ResponseEntity.ok(remoteControllers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteController> getRemoteControllerById(@PathVariable Long id){
        RemoteController remoteController = remoteControllerRepository.getById(id);
        return ResponseEntity.ok(remoteController);
    }

    @PostMapping
    public ResponseEntity<RemoteController> addRemoteController(@RequestBody RemoteController remoteController){
        RemoteController savedRemoteController = remoteControllerRepository.save(remoteController);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRemoteController);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteController> updateRemoteController(@PathVariable Long id, @RequestBody RemoteController remoteControllerDetails){
       var currentRemoteController = remoteControllerRepository.findById(id);
       if (currentRemoteController.isEmpty()){
           return ResponseEntity.notFound().build();

       }else {
           RemoteController updatedRemoteController = currentRemoteController.get();
           updatedRemoteController.setCompatibleWith(remoteControllerDetails.getCompatibleWith());
           updatedRemoteController.setBatteryType(remoteControllerDetails.getBatteryType());
           updatedRemoteController.setName(remoteControllerDetails.getName());
           updatedRemoteController.setBrand(remoteControllerDetails.getBrand());
           updatedRemoteController.setPrice(remoteControllerDetails.getPrice());
           updatedRemoteController.setOriginalStock(remoteControllerDetails.getOriginalStock());
           return ResponseEntity.ok(updatedRemoteController);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RemoteController> deleteRemoteController(@PathVariable Long id){
        remoteControllerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
