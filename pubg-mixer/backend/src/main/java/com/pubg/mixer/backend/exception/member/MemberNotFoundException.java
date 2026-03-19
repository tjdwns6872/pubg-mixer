package com.pubg.mixer.backend.exception.member;

import lombok.Getter;

/**
 * 요청한 멤버 리소스가 존재하지 않을 때 발생하는 예외.
 *
 * <p>`MemberExceptionHandler`에서 404 Not Found로 매핑된다.</p>
 */

@Getter
public class MemberNotFoundException extends RuntimeException {

    private final Long memberId;

    public MemberNotFoundException(Long memberId) {
        super("멤버를 찾을 수 없습니다. memberId=" + memberId);
        this.memberId = memberId;
    }
}