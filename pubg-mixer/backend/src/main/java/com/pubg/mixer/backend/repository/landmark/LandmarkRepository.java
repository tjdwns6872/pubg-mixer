package com.pubg.mixer.backend.repository.landmark;

import com.pubg.mixer.backend.entity.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long>, LandmarkRepositoryCustom {
}
