package com.pubg.mixer.backend.repository.landmark;

import com.pubg.mixer.backend.dto.LandmarkDto;
import com.pubg.mixer.backend.dto.LandmarkFindRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.pubg.mixer.backend.entity.QLandmark.landmark;
import static com.pubg.mixer.backend.entity.QPubgMap.pubgMap;
import static com.pubg.mixer.backend.repository.landmark.support.LandmarkExpressions.mapContains;
import static com.pubg.mixer.backend.repository.landmark.support.LandmarkExpressions.nameContains;

@RequiredArgsConstructor
public class LandmarkRepositoryCustomImpl implements LandmarkRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LandmarkDto> getLandmarks(LandmarkFindRequest request) {
        return jpaQueryFactory
                .select(Projections.constructor(LandmarkDto.class,
                        landmark.id,
                        pubgMap.name,
                        landmark.name,
                        landmark.xCoord,
                        landmark.yCoord,
                        landmark.radius
                ))
                .from(landmark)
                .join(landmark.map, pubgMap)
                .where(
                        nameContains(request.getName()),
                        mapContains(request.getMap())
                )
                .fetch();
    }
}
