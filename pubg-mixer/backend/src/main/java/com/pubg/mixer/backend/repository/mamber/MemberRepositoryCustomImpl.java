package com.pubg.mixer.backend.repository.mamber;

import com.pubg.mixer.backend.dto.MemberDto;
import static com.pubg.mixer.backend.entity.QMember.member;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.pubg.mixer.backend.repository.mamber.support.MemberExpressions.*;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberDto> search(String keyword) {
        return jpaQueryFactory
                .select(Projections.constructor(MemberDto.class
                    , member.id
                    , member.nickname
                    , member.tier
                    , member.createdAt
                ))
                .from(member)
                .where(
                        niknameEq(keyword)
                )
                .fetch();
    }
}
