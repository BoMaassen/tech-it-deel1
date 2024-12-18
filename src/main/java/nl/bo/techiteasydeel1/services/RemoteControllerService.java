package nl.bo.techiteasydeel1.services;

import nl.bo.techiteasydeel1.dtos.RemoteControllerDto;
import nl.bo.techiteasydeel1.dtos.RemoteControllerInputDto;
import nl.bo.techiteasydeel1.exceptions.RecordNotFoundException;
import nl.bo.techiteasydeel1.models.RemoteController;
import nl.bo.techiteasydeel1.repositories.RemoteControllerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public RemoteControllerDto toRemoteControllerDto(RemoteController remoteController){
        RemoteControllerDto dto = new RemoteControllerDto();
        dto.setId(remoteController.getId());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setName(remoteController.getName());
        dto.setBrand(remoteController.getBrand());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());
        return dto;
    }

    public RemoteController toRemoteController(RemoteControllerInputDto dto){
        var remotecontroller = new RemoteController();
        remotecontroller.setCompatibleWith(dto.getCompatibleWith());
        remotecontroller.setBatteryType(dto.getBatteryType());
        remotecontroller.setName(dto.getName());
        remotecontroller.setBrand(dto.getBrand());
        remotecontroller.setPrice(dto.getPrice());
        remotecontroller.setOriginalStock(dto.getOriginalStock());
        return remotecontroller;
    }

    public List<RemoteControllerDto> getRemoteControllers(){
    List<RemoteController> rcList = remoteControllerRepository.findAll();
    List<RemoteControllerDto> rcDtoList = new ArrayList<>();
    for (RemoteController remoteController : rcList){
        RemoteControllerDto dto = toRemoteControllerDto(remoteController);
        rcDtoList.add(dto);
    }
            return rcDtoList;
    }

    public RemoteControllerDto remoteControllerById(Long id){
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(id);
        if (remoteControllerOptional.isPresent()){
            RemoteController remoteController = remoteControllerOptional.get();
            return toRemoteControllerDto(remoteController);
        }else {
            throw new RecordNotFoundException("Geen afstandsbediening gevonden");
        }
    }

    public RemoteControllerDto saveRemoteController(RemoteControllerInputDto dto){
        RemoteController remoteController = toRemoteController(dto);
        remoteControllerRepository.save(remoteController);

        return toRemoteControllerDto(remoteController);
    }

    public  void deleteRemoteController(Long id){
        remoteControllerRepository.deleteById(id);
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto remotecontroller){
        Optional<RemoteController> currentRemoteController = remoteControllerRepository.findById(id);
        if (currentRemoteController.isPresent()){
            RemoteController updatedRemoteController = currentRemoteController.get();
            updatedRemoteController.setCompatibleWith(remotecontroller.getCompatibleWith());
            updatedRemoteController.setBatteryType(remotecontroller.getBatteryType());
            updatedRemoteController.setName(remotecontroller.getName());
            updatedRemoteController.setBrand(remotecontroller.getBrand());
            updatedRemoteController.setPrice(remotecontroller.getPrice());
            updatedRemoteController.setOriginalStock(remotecontroller.getOriginalStock());
            RemoteController returnRemoteController = remoteControllerRepository.save(updatedRemoteController);

            return toRemoteControllerDto(returnRemoteController);
        }else {
            throw new RecordNotFoundException("Geen afstandsbediening gevonden");
        }
    }

}
