package com.pubg.mixer.backend.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pubg.mixer.backend.exception.member.ValidateDuplicateNickname;
import com.pubg.mixer.backend.exception.member.ValidateRequestDuplicateNickname;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.entity.Member;
import com.pubg.mixer.backend.mapper.MemberMapper;
import com.pubg.mixer.backend.repository.mamber.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
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
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> nicknameSet = new HashSet<>(dtos.size());

        for(MemberDto dto: dtos){
            if(!nicknameSet.add(dto.getNickname())){
                throw new ValidateRequestDuplicateNickname();
            }
        }

        try {
            List<Member> entities = memberMapper.toEntityList(dtos);
            List<Member> saved = memberRepository.saveAll(entities);

            return memberMapper.toDtoList(saved);

        } catch (DataIntegrityViolationException e) {
            throw new ValidateDuplicateNickname();
        }
    }
}
