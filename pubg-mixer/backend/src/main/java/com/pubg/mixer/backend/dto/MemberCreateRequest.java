package com.pubg.mixer.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor 
@Builder 
public class MemberCreateRequest {
    @Valid
    @NotEmpty
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @Size(max = 30, message = "멤버는 최대 30명까지 등록할 수 있습니다.")
    private List<MemberDto> members;
}