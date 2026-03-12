package com.pubg.mixer.backend.service;

import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.repository.mamber.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    /**
     * 회원 검색 비즈니스 로직을 처리한다.
     * 레포지토리의 QueryDSL 구현체를 통해 동적 쿼리를 실행하며,
     * 검색 결과인 Entity를 DTO 구조로 변환하여 반환한다.
     */
    @Transactional(readOnly = true)
    public List<MemberDto> getMembers(String keyword) {
        return memberRepository.search(keyword);
    }
}
