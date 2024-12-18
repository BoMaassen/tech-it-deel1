package nl.bo.techiteasydeel1.services;

import nl.bo.techiteasydeel1.dtos.IdInputDto;
import nl.bo.techiteasydeel1.dtos.TelevisionDto;
import nl.bo.techiteasydeel1.dtos.TelevisionInputDto;
import nl.bo.techiteasydeel1.exceptions.RecordNotFoundException;
import nl.bo.techiteasydeel1.models.RemoteController;
import nl.bo.techiteasydeel1.models.Television;
import nl.bo.techiteasydeel1.repositories.RemoteControllerRepository;
import nl.bo.techiteasydeel1.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public TelevisionDto toTelevisionDto(Television television) {
        TelevisionDto dto = new TelevisionDto();
        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getSmartTv());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());
        return dto;
    }


    public Television toTelevision(TelevisionInputDto dto) {
        var television = new Television();
        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());
        return television;

    }

    public List<TelevisionDto> getTelevision() {
        List<Television> tvList = televisionRepository.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for (Television tv : tvList) {
            TelevisionDto dto = toTelevisionDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    public TelevisionDto televisionById(Long id) {
        Optional<Television> televisionOptional = televisionRepository.findById(id);
        if (televisionOptional.isPresent()) {
            Television tv = televisionOptional.get();
            return toTelevisionDto(tv);
        } else {
            throw new RecordNotFoundException("geen televisie gevonden");
        }
    }

    public TelevisionDto saveTelevision(TelevisionInputDto dto) {

        Television tv = toTelevision(dto);
        televisionRepository.save(tv);

        return toTelevisionDto(tv);
    }

    public void deleteTelevision(Long id) {
        televisionRepository.deleteById(id);

    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto television) {

        Optional<Television> currentTv = televisionRepository.findById(id);
        if (currentTv.isPresent()) {
            Television updatedTv = currentTv.get();
            updatedTv.setType(television.getType());
            updatedTv.setBrand(television.getBrand());
            updatedTv.setName(television.getName());
            updatedTv.setPrice(television.getPrice());
            updatedTv.setAvailableSize(television.getAvailableSize());
            updatedTv.setRefreshRate(television.getRefreshRate());
            updatedTv.setScreenType(television.getScreenType());
            updatedTv.setScreenQuality(television.getScreenQuality());
            updatedTv.setSmartTv(television.getSmartTv());
            updatedTv.setWifi(television.getWifi());
            updatedTv.setVoiceControl(television.getVoiceControl());
            updatedTv.setHdr(television.getHdr());
            updatedTv.setBluetooth(television.getBluetooth());
            updatedTv.setAmbiLight(television.getAmbiLight());
            updatedTv.setOriginalStock(television.getOriginalStock());
            updatedTv.setSold(television.getSold());
            Television returnTv = televisionRepository.save(updatedTv);
            return toTelevisionDto(returnTv);
        } else {
            throw new RecordNotFoundException("Geen televisie gevonden");
        }
    }

    public TelevisionDto assignRemoteControllerToTelevision(Long televisionId, IdInputDto inputDto){
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Geen televisie gevonden met id " + televisionId));
        RemoteController remoteController = remoteControllerRepository.findById(inputDto.id).orElseThrow(() -> new RecordNotFoundException("Geen afstandsbediening gevonden met id " + inputDto));

        television.setRemoteController(remoteController);
        televisionRepository.save(television);

        return toTelevisionDto(television);

    }



}
