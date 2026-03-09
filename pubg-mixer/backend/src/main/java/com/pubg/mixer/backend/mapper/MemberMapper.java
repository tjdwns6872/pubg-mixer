package com.pubg.mixer.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pubg.mixer.backend.dto.MemberDto;
import com.pubg.mixer.backend.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDto, Member> {
    
    List<Member> toEntityList(List<MemberDto> dtos);
    List<MemberDto> toDtoList(List<Member> entities);
}
