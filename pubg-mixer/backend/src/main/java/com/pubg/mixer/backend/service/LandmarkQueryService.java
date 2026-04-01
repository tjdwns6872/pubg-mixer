package com.pubg.mixer.backend.service;

import com.pubg.mixer.backend.dto.LandmarkDto;
import com.pubg.mixer.backend.dto.LandmarkFindRequest;
import com.pubg.mixer.backend.exception.landmark.LandmarkNotFoundException;
import com.pubg.mixer.backend.repository.landmark.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkQueryService {

    private final LandmarkRepository landmarkRepository;

    @Transactional(readOnly = true)
    public List<LandmarkDto> getLandmarks(LandmarkFindRequest request) {
        if (request.getName() != null && request.getName().isBlank()) {
            throw new IllegalArgumentException("name은 공백일 수 없습니다.");
        }
        if (request.getMap() != null && request.getMap().isBlank()) {
            throw new IllegalArgumentException("map은 공백일 수 없습니다.");
        }

        List<LandmarkDto> landmarks = landmarkRepository.getLandmarks(request);
        if (landmarks.isEmpty()) {
            throw new LandmarkNotFoundException();
        }
        return landmarks;
    }
}
