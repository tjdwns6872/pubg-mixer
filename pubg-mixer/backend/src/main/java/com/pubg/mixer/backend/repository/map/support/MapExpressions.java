package com.pubg.mixer.backend.repository.map.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;
import static com.pubg.mixer.backend.entity.QPubgMap.pubgMap;

public class MapExpressions {

    public static BooleanExpression nameEq(String name){
        if(!StringUtils.hasText(name)){
            return null;
        }
        return pubgMap.name.startsWith(name);
    }

    public static BooleanExpression idEq(Long id){
        if(id == null || id == 0L){
            return null;
        }
        return pubgMap.id.eq(id);
    }

    public static BooleanExpression mapSizeKmEq(Integer mapSizeKm){
        if(mapSizeKm == null || mapSizeKm == 0){
            return null;
        }
        return pubgMap.mapSizeKm.eq(mapSizeKm);
    }
}
