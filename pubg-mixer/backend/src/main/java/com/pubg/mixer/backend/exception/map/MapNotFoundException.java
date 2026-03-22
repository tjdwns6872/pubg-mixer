package com.pubg.mixer.backend.exception.map;

import lombok.Getter;

@Getter
public class MapNotFoundException extends RuntimeException {

    private final Long mapId;

    public MapNotFoundException(Long mapId) {
        super("맵을 찾을 수 없습니다. mapId=" + mapId);
        this.mapId = mapId;
    }
}
