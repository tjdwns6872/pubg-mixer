package com.pubg.mixer.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.service.MemberService;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    /**
     * 단건 또는 복수 멤버 등록을 처리한다.
     * JSON 배열이나 단일 객체 모두 허용된다.
     */
    @PostMapping("/members")
    public ResponseEntity<List<MemberDto>> createMembers(
            @RequestBody @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            List<MemberDto> members) {
        List<MemberDto> saved = memberService.saveMembers(members);
        return ResponseEntity.status(201).body(saved);
    }
}
