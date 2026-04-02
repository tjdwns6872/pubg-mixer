package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.dto.LandmarkDto;
import com.pubg.mixer.backend.dto.LandmarkFindRequest;
import com.pubg.mixer.backend.service.LandmarkQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LandmarkController {

    private final LandmarkQueryService landmarkQueryService;

    @GetMapping("/landmarks")
    public ResponseEntity<CommonResponse<List<LandmarkDto>>> readLandmarks(
            @Valid @ModelAttribute LandmarkFindRequest request
    ) {
        List<LandmarkDto> find = landmarkQueryService.getLandmarks(request);
        return ResponseEntity.ok(CommonResponse.success(find));
    }
}
