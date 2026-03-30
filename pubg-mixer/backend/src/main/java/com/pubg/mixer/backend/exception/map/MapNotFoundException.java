package com.pubg.mixer.backend.exception.map;

import lombok.Getter;

@Getter
public class MapNotFoundException extends RuntimeException {

    public MapNotFoundException() {
        super("해당 조건에 맞는 맵이 존재하지 않습니다.");
    }
}
