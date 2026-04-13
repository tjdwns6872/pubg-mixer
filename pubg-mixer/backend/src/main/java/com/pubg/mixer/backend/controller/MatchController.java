package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.external.service.pubg.PubgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MatchController {

    private final PubgService pubgService;

    @GetMapping("/match")
    public ResponseEntity<CommonResponse<Void>> readMatch(@RequestParam String observerNick){
        pubgService.insertResult(observerNick, 5);
        return ResponseEntity.ok(CommonResponse.success());
    }
}
