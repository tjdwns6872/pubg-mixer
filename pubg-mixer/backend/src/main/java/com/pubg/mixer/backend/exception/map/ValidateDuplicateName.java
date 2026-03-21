package com.pubg.mixer.backend.exception.map;

import org.springframework.dao.DataIntegrityViolationException;

public class ValidateDuplicateName extends DataIntegrityViolationException {
    public ValidateDuplicateName() {
        super("이미 존재하는 맵입니다.");
    }
}
