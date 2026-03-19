package com.pubg.mixer.backend.exception.member;

/**
 * 멤버 수정 요청이 비정상적인 경우(수정할 값 없음, 공백 닉네임 등)에 사용되는 예외.
 *
 * <p>비즈니스 입력 오류로 간주하여 400 Bad Request로 매핑된다.</p>
 */
public class InvalidMemberUpdateRequestException extends IllegalArgumentException {

    public InvalidMemberUpdateRequestException(String message) {
        super(message);
    }
}

