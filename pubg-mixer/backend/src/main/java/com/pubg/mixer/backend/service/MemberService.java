package com.pubg.mixer.backend.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pubg.mixer.backend.dto.MemberUpdateRequest;
import com.pubg.mixer.backend.exception.member.InvalidMemberUpdateRequestException;
import com.pubg.mixer.backend.exception.member.MemberNotFoundException;
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
     * 여러 멤버를 저장한다.
     *
     * <p>개수가 1개든 여러 개든 리스트로 받아 처리한다.</p>
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

    /**
     * 멤버 정보를 부분 수정한다.
     *
     * <p>요청은 PATCH 기반이며, 수정하고 싶은 값만 전달될 수 있다.</p>
     * <p>따라서 아래 순서로 방어적으로 처리한다.</p>
     *
     * <ul>
     *     <li>요청에 수정할 값이 하나도 없으면 400</li>
     *     <li>닉네임이 공백이면 400</li>
     *     <li>대상 멤버가 없으면 404</li>
     *     <li>닉네임 변경 시 다른 멤버가 이미 사용하는 닉네임이면 409</li>
     *     <li>DB 유니크 제약 위반도 409로 변환한다(경쟁 상황 대비).</li>
     * </ul>
     */
    @Transactional
    public MemberDto updateMember(Long memberId, MemberUpdateRequest request) {
        if (request == null) {
            throw new InvalidMemberUpdateRequestException("요청 본문이 비어있습니다.");
        }

        boolean hasNickname = request.getNickname() != null;
        boolean hasTier = request.getTier() != null;
        if (!hasNickname && !hasTier) {
            throw new InvalidMemberUpdateRequestException("수정할 값이 없습니다.");
        }

        if (hasNickname && request.getNickname().isBlank()) {
            throw new InvalidMemberUpdateRequestException("닉네임은 공백일 수 없습니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        /*
         * 닉네임이 실제로 변경되는 경우에만 중복 체크를 수행한다.
         *
         * 동일 값으로의 업데이트 요청에 대해 불필요한 DB 조회를 하지 않기 위함이다.
         */
        if (hasNickname && !request.getNickname().equals(member.getNickname())) {
            if (memberRepository.existsByNicknameAndIdNot(request.getNickname(), memberId)) {
                throw new ValidateDuplicateNickname();
            }
        }

        try {
            member.update(request.getNickname(), request.getTier());
            return memberMapper.toDto(member);
        } catch (DataIntegrityViolationException e) {
            /*
             * 동시성 상황에서도 일관된 응답을 준다.
             *
             * 애플리케이션 레벨에서 선제적으로 중복 체크를 하더라도,
             * 거의 동시에 동일 닉네임으로 업데이트하면 DB 유니크 제약에서 충돌할 수 있다.
             */
            throw new ValidateDuplicateNickname();
        }
    }
}
