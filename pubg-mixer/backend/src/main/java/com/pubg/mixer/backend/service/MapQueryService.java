package com.pubg.mixer.backend.service;

import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.dto.MapFindRequest;
import com.pubg.mixer.backend.exception.map.MapNotFoundException;
import com.pubg.mixer.backend.repository.map.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapQueryService {

    private final MapRepository mapRepository;

    @Transactional(readOnly = true)
    public List<MapDto> getMaps(MapFindRequest request){
        List<MapDto> maps = mapRepository.getMaps(request);
        if (maps.isEmpty()){
            throw new MapNotFoundException();
        }
        return mapRepository.getMaps(request);
    }
}
