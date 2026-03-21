package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.service.MapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class MapController {

    private final MapService mapService;

    @PostMapping("/map")
    public ResponseEntity<CommonResponse<Void>> createMap(@RequestBody @Valid MapDto dto) {
        mapService.createMap(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success());
    }
}