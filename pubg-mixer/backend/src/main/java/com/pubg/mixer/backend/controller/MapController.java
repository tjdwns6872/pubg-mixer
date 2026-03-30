package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.service.MapQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class MapController {

    private final MapQueryService mapQueryService;

    @GetMapping("/maps")
    public ResponseEntity<CommonResponse<List<MapDto>>> readMaps(){
        List<MapDto> find = mapQueryService.getMaps();
        return ResponseEntity.ok(CommonResponse.success(find));
    }
}