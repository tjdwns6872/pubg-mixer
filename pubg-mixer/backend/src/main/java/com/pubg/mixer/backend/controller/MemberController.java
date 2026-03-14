package com.pubg.mixer.backend.controller;

import com.pubg.mixer.backend.service.MemberQueryService;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final MemberQueryService memberQueryService;

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

    /**
     * 검색 키워드를 기반으로 회원 목록을 조회한다.
     * 키워드가 없을 경우 전체 목록을 반환하며, 인덱스를 활용한 최적화된 조회를 수행한다.
     * @param keyword 검색할 닉네임 (Optional)
     * @return 검색 조건에 부합하는 멤버 정보 리스트 (MemberDto)
     */
    @GetMapping("/members")
    public ResponseEntity<CommonResponse<List<MemberDto>>> readMembers(@RequestParam(required = false) @Size(max = 20) String keyword){
        List<MemberDto> find = memberQueryService.getMembers(keyword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(find));
    }
}
