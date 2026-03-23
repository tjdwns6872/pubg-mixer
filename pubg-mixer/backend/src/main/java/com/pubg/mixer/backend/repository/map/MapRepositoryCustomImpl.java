package com.pubg.mixer.backend.repository.map;

import com.pubg.mixer.backend.dto.MapDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.pubg.mixer.backend.entity.QPubgMap.pubgMap;
import java.util.List;

@RequiredArgsConstructor
public class MapRepositoryCustomImpl implements MapRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MapDto> getMaps() {
        return jpaQueryFactory
                .select(Projections.constructor(MapDto.class
                        , pubgMap.id
                        , pubgMap.name
                        , pubgMap.mapNameInternal
                        , pubgMap.mapSizeKm
                        , pubgMap.imagePath
                        , pubgMap.maxCoordinate
                ))
                .from(pubgMap)
                .fetch();
    }
}
