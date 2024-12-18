package nl.bo.techiteasydeel1.services;

import nl.bo.techiteasydeel1.dtos.CIModuleDto;
import nl.bo.techiteasydeel1.dtos.CIModuleInputDto;
import nl.bo.techiteasydeel1.dtos.TelevisionInputDto;
import nl.bo.techiteasydeel1.exceptions.RecordNotFoundException;
import nl.bo.techiteasydeel1.models.CIModule;
import nl.bo.techiteasydeel1.repositories.CIModulesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {

    private final CIModulesRepository ciModulesRepository;

    public CIModuleService(CIModulesRepository ciModulesRepository) {
        this.ciModulesRepository = ciModulesRepository;
    }

    public CIModuleDto toCIModuleDto(CIModule ciModule){
        CIModuleDto dto = new CIModuleDto();
        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());
        return dto;
    }

    public CIModule toCIModule(CIModuleInputDto dto){
        var ciModule = new CIModule();
        ciModule.setName(dto.getName());
        ciModule.setType(dto.getType());
        ciModule.setPrice(dto.getPrice());
        return ciModule;
    }


    public List<CIModuleDto> getCIModules(){
        List<CIModule> ciModules = ciModulesRepository.findAll();
        List<CIModuleDto> ciModuleDtoList = new ArrayList<>();
        for (CIModule ciModule : ciModules){
            CIModuleDto dto = toCIModuleDto(ciModule);
            ciModuleDtoList.add(dto);
        }
        return ciModuleDtoList;
    }

    public CIModuleDto ciModuleById(Long id){
        Optional<CIModule> ciModuleOptional = ciModulesRepository.findById(id);
        if (ciModuleOptional.isPresent()){
            CIModule ciModule = ciModuleOptional.get();
            return toCIModuleDto(ciModule);
        }else throw new RecordNotFoundException("Geen CI module gevonden");
    }

    public CIModuleDto saveCIModule(CIModuleInputDto dto){
        CIModule ciModule = toCIModule(dto);
        ciModulesRepository.save(ciModule);

        return toCIModuleDto(ciModule);
    }

    public void deleteCIModule(Long id){
        ciModulesRepository.deleteById(id);
    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto cimodule){
       Optional<CIModule> ciModuleOptional = ciModulesRepository.findById(id);
        if (ciModuleOptional.isPresent()){
            CIModule updatedCIModule = ciModuleOptional.get();
            updatedCIModule.setName(cimodule.getName());
            updatedCIModule.setType(cimodule.getType());
            updatedCIModule.setPrice(cimodule.getPrice());
            CIModule returnCIModule = ciModulesRepository.save(updatedCIModule);

            return toCIModuleDto(returnCIModule);

        }else {
            throw new RecordNotFoundException("Geen Ci Module gevonden");
        }
    }
}
