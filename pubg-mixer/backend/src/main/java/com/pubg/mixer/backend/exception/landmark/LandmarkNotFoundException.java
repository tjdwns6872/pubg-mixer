package com.pubg.mixer.backend.exception.landmark;

import lombok.Getter;

@Getter
public class LandmarkNotFoundException extends RuntimeException {

    public LandmarkNotFoundException() {
        super("해당 조건에 맞는 랜드마크가 존재하지 않습니다.");
    }
}
