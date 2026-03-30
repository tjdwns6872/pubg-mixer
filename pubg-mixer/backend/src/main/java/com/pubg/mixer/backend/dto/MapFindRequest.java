package com.pubg.mixer.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapFindRequest {

    @PositiveOrZero
    private Long id;

    @Size(max = 20)
    private String name;

    @Min(4) @Max(8)
    private Integer mapSizeKm;

}
