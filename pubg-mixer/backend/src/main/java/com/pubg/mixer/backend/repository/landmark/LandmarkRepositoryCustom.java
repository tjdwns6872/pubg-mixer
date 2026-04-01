package com.pubg.mixer.backend.repository.landmark;

import com.pubg.mixer.backend.dto.LandmarkDto;
import com.pubg.mixer.backend.dto.LandmarkFindRequest;

import java.util.List;

public interface LandmarkRepositoryCustom {
    List<LandmarkDto> getLandmarks(LandmarkFindRequest request);
}
