package com.pubg.mixer.backend.mapper;

import com.pubg.mixer.backend.dto.MapDto;
import com.pubg.mixer.backend.entity.PubgMap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapMapper extends EntityMapper<MapDto, PubgMap>{
}
