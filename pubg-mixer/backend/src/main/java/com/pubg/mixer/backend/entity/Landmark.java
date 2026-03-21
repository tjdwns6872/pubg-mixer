package com.pubg.mixer.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "landmark")
@Getter
public class Landmark {
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id", nullable = false)
    private PubgMap map;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "x_coord", nullable = false)
    private Float xCoord;

    @Column(name = "y_coord", nullable = false)
    private Float yCoord;

    @Column(name = "radius")
    private Float radius;

    @PrePersist
    public void prePersist() {
        if (xCoord == null) {
            xCoord = 0.0f;
        }
        if (yCoord == null) {
            yCoord = 0.0f;
        }
        if (radius == null) {
            radius = 5000.0f;
        }
    }
}
