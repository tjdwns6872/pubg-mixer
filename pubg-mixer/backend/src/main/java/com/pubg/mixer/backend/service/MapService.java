package com.pubg.mixer.backend.service;

import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.dto.MapUpdateRequest;
import com.pubg.mixer.backend.entity.PubgMap;
import com.pubg.mixer.backend.exception.map.MapNotFoundException;
import com.pubg.mixer.backend.exception.map.ValidateDuplicateName;
import com.pubg.mixer.backend.mapper.MapMapper;
import com.pubg.mixer.backend.repository.map.MapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;
    private final MapMapper mapMapper;

    @Transactional
    public MapDto createMap(MapDto dto) {
        try {
            PubgMap map = mapMapper.toEntity(dto);
            PubgMap saved = mapRepository.save(map);
            return mapMapper.toDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new ValidateDuplicateName();
        }
    }

    @Transactional
    public MapDto updateMap(Long mapId, MapUpdateRequest request) {
        PubgMap map = mapRepository.findById(mapId)
                .orElseThrow(() -> new MapNotFoundException(mapId));

        if (request.getMapNameInternal() != null
                && !request.getMapNameInternal().equals(map.getMapNameInternal())) {
            if (mapRepository.existsByMapNameInternalAndIdNot(request.getMapNameInternal(), mapId)) {
                throw new ValidateDuplicateName();
            }
        }

        try {
            map.update(
                    request.getName(),
                    request.getMapNameInternal(),
                    request.getMapSizeKm(),
                    request.getImagePath(),
                    request.getMaxCoordinate()
            );
            mapRepository.flush();
            return mapMapper.toDto(map);
        } catch (DataIntegrityViolationException e) {
            throw new ValidateDuplicateName();
        }
    }
}
