package com.pubg.mixer.backend.repository.mamber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pubg.mixer.backend.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    /**
     * 닉네임 중복 여부를 빠르게 판단하기 위한 쿼리 메서드.
     *
     * <p>업데이트 시 내 id를 제외하고 동일 닉네임이 존재하는지 확인한다.</p>
     */
    boolean existsByNicknameAndIdNot(String nickname, Long id);
}
