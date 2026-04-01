package com.pubg.mixer.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandmarkDto {

    private Long id;
    private String map;
    private String name;
    private Float xCoord;
    private Float yCoord;
    private Float radius;
}
