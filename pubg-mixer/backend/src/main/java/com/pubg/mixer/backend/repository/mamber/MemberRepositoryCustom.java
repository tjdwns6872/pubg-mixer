package com.pubg.mixer.backend.repository.mamber;

import com.pubg.mixer.backend.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberDto> search(String keyword);
}
