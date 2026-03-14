package com.pubg.mixer.backend.repository.mamber.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;
import static com.pubg.mixer.backend.entity.QMember.member;
public class MemberExpressions {

    public static BooleanExpression niknameEq(String keyword){
        if(!StringUtils.hasText(keyword)){
            return null;
        }
        return member.nickname.startsWith(keyword);
    }
}
