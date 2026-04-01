package com.pubg.mixer.backend.repository.landmark.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static com.pubg.mixer.backend.entity.QLandmark.landmark;
import static com.pubg.mixer.backend.entity.QPubgMap.pubgMap;

public class LandmarkExpressions {

    public static BooleanExpression nameContains(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        return landmark.name.containsIgnoreCase(name.trim());
    }

    public static BooleanExpression mapContains(String mapKeyword) {
        if (!StringUtils.hasText(mapKeyword)) {
            return null;
        }
        String keyword = mapKeyword.trim();
        return pubgMap.name.containsIgnoreCase(keyword)
                .or(pubgMap.mapNameInternal.containsIgnoreCase(keyword));
    }
}
