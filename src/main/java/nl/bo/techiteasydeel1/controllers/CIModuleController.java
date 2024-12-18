package nl.bo.techiteasydeel1.controllers;


import nl.bo.techiteasydeel1.dtos.CIModuleDto;
import nl.bo.techiteasydeel1.dtos.CIModuleInputDto;
import nl.bo.techiteasydeel1.services.CIModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ci-modules")

public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getCIModules() {
        List<CIModuleDto> responseDto = ciModuleService.getCIModules();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModuleById(@PathVariable Long id) {
        CIModuleDto dto = ciModuleService.ciModuleById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto ciModuleDto = ciModuleService.saveCIModule(ciModuleInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ciModuleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable Long id, @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto ciModuleDto = ciModuleService.updateCIModule(id, ciModuleInputDto);
        return ResponseEntity.ok(ciModuleDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable Long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }
}
