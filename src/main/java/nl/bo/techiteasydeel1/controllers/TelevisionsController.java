package nl.bo.techiteasydeel1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TelevisionsController {

    @GetMapping("/televisions")
    public ResponseEntity<String> getTelevisions(){
        return ResponseEntity.ok("television");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<String> getTelevision(@PathVariable("id") int id){
        return ResponseEntity.ok("television");
    }

    @PostMapping("/televisions")
    public ResponseEntity<String> createTv(@RequestBody String television){
        return ResponseEntity.created(null).body("television");

    }
    @PutMapping ("/televisions/{id}")
    public ResponseEntity<String> updateTv(@PathVariable int id, @RequestBody String television){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<String> deleteTv(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }


}
