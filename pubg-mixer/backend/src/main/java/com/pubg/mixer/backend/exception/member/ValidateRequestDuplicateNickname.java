package com.pubg.mixer.backend.exception.member;

public class ValidateRequestDuplicateNickname extends IllegalArgumentException{

    public ValidateRequestDuplicateNickname(){
        super("요청에 중복 닉네임 존재");
    }
}
