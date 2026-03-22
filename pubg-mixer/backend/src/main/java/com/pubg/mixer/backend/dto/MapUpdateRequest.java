package com.pubg.mixer.backend.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 맵 부분 수정용 요청. 전달된 필드만 갱신한다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapUpdateRequest {

    /** 표기용 이름 (map_name_display) */
    @Size(max = 50, message = "name은 50자 이하여야 합니다.")
    private String name;

    /** API 원본 이름 (map_name_internal), 유니크 */
    @Size(max = 50, message = "mapNameInternal은 50자 이하여야 합니다.")
    private String mapNameInternal;

    private Integer mapSizeKm;

    @Size(max = 255, message = "imagePath는 255자 이하여야 합니다.")
    private String imagePath;

    private Float maxCoordinate;
}
