package com.pubg.mixer.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "map")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PubgMap {
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "map_name_display", nullable = false, length = 50)
    private String name;

    @Column(name = "map_name_internal", nullable = false, unique = true, length = 50)
    private String mapNameInternal;

    @Column(name = "map_size_km")
    private Integer mapSizeKm;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @Column(name = "max_coordinate")
    private Float maxCoordinate;

    @PrePersist
    public void prePersist() {
        if (mapNameInternal == null || mapNameInternal.isBlank()) {
            mapNameInternal = name;
        }
        if (maxCoordinate == null) {
            maxCoordinate = 816000.0f;
        }
    }
}
