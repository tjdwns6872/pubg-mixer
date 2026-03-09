package com.pubg.mixer.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.entity.Member;
import com.pubg.mixer.backend.mapper.MemberMapper;
import com.pubg.mixer.backend.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    /**
     * 여러 멤버를 저장한다. 개수가 1개든 여러개든 리스트로 받아 처리.
     *
     * @param dtos 저장할 멤버 DTO 목록
     * @return 저장된 멤버 DTO 목록(아이디 포함)
     */
    @Transactional
    public List<MemberDto> saveMembers(List<MemberDto> dtos) {
        List<Member> entities = memberMapper.toEntityList(dtos);

        List<Member> saved = memberRepository.saveAll(entities);

        return memberMapper.toDtoList(saved);
    }
}
