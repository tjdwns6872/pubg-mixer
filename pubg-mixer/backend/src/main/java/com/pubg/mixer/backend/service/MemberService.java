package com.pubg.mixer.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.entity.Member;
import com.pubg.mixer.backend.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 여러 멤버를 저장한다. 개수가 1개든 여러개든 리스트로 받아 처리.
     *
     * @param dtos 저장할 멤버 DTO 목록
     * @return 저장된 멤버 DTO 목록(아이디 포함)
     */
    @Transactional
    public List<MemberDto> saveMembers(List<MemberDto> dtos) {
        List<Member> entities = dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        List<Member> saved = memberRepository.saveAll(entities);

        return saved.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private Member toEntity(MemberDto dto) {
        return Member.builder()
                .id(dto.getId())
                .nickname(dto.getNickname())
                .tier(dto.getTier() == null ? 0 : dto.getTier())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private MemberDto toDto(Member entity) {
        return MemberDto.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .tier(entity.getTier())
                .build();
    }
}
