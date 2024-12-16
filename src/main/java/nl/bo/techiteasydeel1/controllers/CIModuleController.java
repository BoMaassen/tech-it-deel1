package nl.bo.techiteasydeel1.controllers;


import nl.bo.techiteasydeel1.models.CIModule;
import nl.bo.techiteasydeel1.repositories.CIModulesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ci-modules")

public class CIModuleController {
    private final CIModulesRepository ciModulesRepository;

    public CIModuleController(CIModulesRepository ciModulesRepository) {
        this.ciModulesRepository = ciModulesRepository;
    }


    @GetMapping
    public ResponseEntity<List<CIModule>> getCIModules(){
        List<CIModule> ciModules = ciModulesRepository.findAll();
        return ResponseEntity.ok(ciModules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModule> getCIModuleById(@PathVariable Long id){
        CIModule ciModule = ciModulesRepository.getById(id);
        return ResponseEntity.ok(ciModule);
    }

    @PostMapping
    public ResponseEntity<CIModule> addCIModule(@RequestBody CIModule ciModule){
        CIModule savedCIModule = ciModulesRepository.save(ciModule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCIModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModule> updateCIModule(@PathVariable Long id, @RequestBody CIModule ciModule){
        var currentCIModule = ciModulesRepository.findById(id);
        if (currentCIModule.isEmpty()){
            return ResponseEntity.notFound().build();

        }else {
            CIModule updatedCIModule = currentCIModule.get();
            updatedCIModule.setName(ciModule.getName());
            updatedCIModule.setType(ciModule.getType());
            updatedCIModule.setPrice(ciModule.getPrice());

            return ResponseEntity.ok(updatedCIModule);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CIModule> deleteCIModule(@PathVariable Long id){
        ciModulesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
