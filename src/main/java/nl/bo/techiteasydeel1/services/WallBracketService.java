package nl.bo.techiteasydeel1.services;

import nl.bo.techiteasydeel1.dtos.WallBracketInputDto;
import nl.bo.techiteasydeel1.dtos.WallBracketDto;
import nl.bo.techiteasydeel1.models.WallBracket;
import nl.bo.techiteasydeel1.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public WallBracketDto toWallBracketDto(WallBracket wallBracket){
        WallBracketDto dto = new WallBracketDto();
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());
        return dto;
    }

    public WallBracket toWallBracket(WallBracketInputDto dto){
        var wallbracket = new WallBracket();
        wallbracket.setSize(dto.getSize());
        wallbracket.setAdjustable(dto.getAdjustable());
        wallbracket.setName(dto.getName());
        wallbracket.setPrice(dto.getPrice());
        return wallbracket;
    }

    public List<WallBracketDto> getWallBrackets(){
        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        List<WallBracketDto> wallBracketDtoList = new ArrayList<>();
        for (WallBracket wallBracket : wallBrackets){
            WallBracketDto wallBracketDto = toWallBracketDto(wallBracket);
            wallBracketDtoList.add(wallBracketDto);
        }
        return wallBracketDtoList;
    }

    public WallBracketDto getWallBracketById(Long id){
        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(id);
        if (wallBracketOptional.isPresent()){
            WallBracket wallBracket = wallBracketOptional.get();
            return toWallBracketDto(wallBracket);
        }else {
            throw new RuntimeException("Geen tv beugel gevonden");
        }
    }

    public WallBracketDto saveWallBracket(WallBracketInputDto dto){
        WallBracket wallBracket = toWallBracket(dto);
        wallBracketRepository.save(wallBracket);
        return toWallBracketDto(wallBracket);
    }

    public void deleteWallBracket(Long id){
        wallBracketRepository.deleteById(id);
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto){
        Optional<WallBracket> currentWallBracket = wallBracketRepository.findById(id);
        if (currentWallBracket.isPresent()){
            WallBracket updatedWallBracket = currentWallBracket.get();
            updatedWallBracket.setSize(wallBracketInputDto.getSize());
            updatedWallBracket.setAdjustable(wallBracketInputDto.getAdjustable());
            updatedWallBracket.setName(wallBracketInputDto.getName());
            updatedWallBracket.setPrice(wallBracketInputDto.getPrice());
            WallBracket returnWallBracket = wallBracketRepository.save(updatedWallBracket);
            return toWallBracketDto(returnWallBracket);
        }else throw new RuntimeException("Geen tv beugel gevonden");
    }

}
