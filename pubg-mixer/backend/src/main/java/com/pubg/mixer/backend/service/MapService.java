package com.pubg.mixer.backend.service;

import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.entity.PubgMap;
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
    public void createMap(MapDto dto){
        try {
            PubgMap map = mapMapper.toEntity(dto);
            mapRepository.save(map);
        } catch (DataIntegrityViolationException e) {
            throw new ValidateDuplicateName();
        }
    }
}
