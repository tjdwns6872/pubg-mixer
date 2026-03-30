package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.dto.MapFindRequest;
import com.pubg.mixer.backend.service.MapQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MapController {

    private final MapQueryService mapQueryService;

    @GetMapping("/maps")
    public ResponseEntity<CommonResponse<List<MapDto>>> readMaps(
            @Valid @ModelAttribute MapFindRequest request
    ){
        List<MapDto> find = mapQueryService.getMaps(request);
        return ResponseEntity.ok(CommonResponse.success(find));
    }
}