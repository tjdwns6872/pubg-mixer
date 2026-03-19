package com.pubg.mixer.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 멤버 정보 부분 수정을 위한 요청 DTO.
 *
 * <p>모든 필드는 선택(optional)로 두고, 실제 필수 여부와 공백/값 존재 여부는
 * 서비스 계층에서 추가로 검증한다.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequest {

    /**
     * 변경할 닉네임 (선택).
     *
     * <p>null이면 닉네임을 수정하지 않는다. 공백 문자열 여부는 서비스에서 검증한다.</p>
     */
    @Size(max = 100)
    private String nickname;

    /**
     * 변경할 티어 (선택).
     *
     * <p>null이면 티어를 수정하지 않는다.</p>
     */
    @Min(1)
    @Max(4)
    @Positive(message = "올바른 티어 ID를 입력해주세요.")
    private Integer tier;
}

