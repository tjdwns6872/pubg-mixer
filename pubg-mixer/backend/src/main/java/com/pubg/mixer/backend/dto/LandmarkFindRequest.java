package com.pubg.mixer.backend.dto;

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
public class LandmarkFindRequest {

    @PositiveOrZero
    private Long id;

    @Size(max = 20)
    private String name;

    @Size(max = 20)
    private String map;
}
