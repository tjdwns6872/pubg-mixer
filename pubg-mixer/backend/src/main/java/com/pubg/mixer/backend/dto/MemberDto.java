package com.pubg.mixer.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;

    @NotBlank(message = "nickname")
    @Size(max = 100)
    private String nickname;

    @NotNull(message = "tier")
    @Min(value = 1)
    @Max(value = 4)
    @Positive(message = "올바른 티어 ID를 입력해주세요.")
    private Integer tier;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
}
