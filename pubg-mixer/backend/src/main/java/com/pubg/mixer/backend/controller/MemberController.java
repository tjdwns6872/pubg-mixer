package com.pubg.mixer.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.pubg.mixer.backend.common.CommonResponse;
import com.pubg.mixer.backend.dto.MemberCreateRequest;
import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.service.MemberService;

import jakarta.validation.Valid;
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
    public ResponseEntity<CommonResponse<List<MemberDto>>> createMembers(@RequestBody @Valid MemberCreateRequest members) {
        List<MemberDto> saved = memberService.saveMembers(members.getMembers());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.success(saved));
    }
}
