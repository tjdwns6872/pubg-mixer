package com.pubg.mixer.backend.repository.map;

import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.dto.MapFindRequest;

import java.util.List;

public interface MapRepositoryCustom {
    List<MapDto> getMaps(MapFindRequest request);
}
