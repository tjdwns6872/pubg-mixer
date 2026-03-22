package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.dto.MapUpdateRequest;
import com.pubg.mixer.backend.service.MapQueryService;
import com.pubg.mixer.backend.service.MapService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class MapController {

    private final MapService mapService;
    private final MapQueryService mapQueryService;

    @PostMapping("/map")
    public ResponseEntity<CommonResponse<MapDto>> createMap(@RequestBody @Valid MapDto dto) {
        MapDto saved = mapService.createMap(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(saved));
    }

    @PatchMapping("/map/{mapId}")
    public ResponseEntity<CommonResponse<MapDto>> updateMap(
            @PathVariable @Positive Long mapId,
            @RequestBody @Valid MapUpdateRequest request
    ) {
        MapDto updated = mapService.updateMap(mapId, request);
        return ResponseEntity.ok(CommonResponse.success(updated));
    }

    @GetMapping("/maps")
    public ResponseEntity<CommonResponse<List<MapDto>>> readMaps(){
        List<MapDto> find = mapQueryService.getMaps();
        return ResponseEntity.ok(CommonResponse.success(find));
    }
}