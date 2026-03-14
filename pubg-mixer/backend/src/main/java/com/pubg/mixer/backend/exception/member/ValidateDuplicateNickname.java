package com.pubg.mixer.backend.exception.member;

import org.springframework.dao.DataIntegrityViolationException;

public class ValidateDuplicateNickname extends DataIntegrityViolationException {
    public ValidateDuplicateNickname(){
        super("이미 존재하는 닉네임이 포함되어있습니다.");
    }
}
