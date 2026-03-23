package com.pubg.mixer.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapDto {

    private Long id;

    @NotBlank(message = "name")
    @Size(max = 50, message = "name은 50자 이하여야 합니다.")
    private String name;

    /** API 원본 이름 (예: Erangel_Main). 비우면 표기용 name과 동일하게 저장된다. */
    @Size(max = 50, message = "mapNameInternal은 50자 이하여야 합니다.")
    private String mapNameInternal;

    private Integer mapSizeKm;

    @Size(max = 255, message = "imagePath는 255자 이하여야 합니다.")
    private String imagePath;

    private Float maxCoordinate;
}
