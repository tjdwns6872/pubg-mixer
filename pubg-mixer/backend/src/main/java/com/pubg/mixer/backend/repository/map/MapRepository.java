package com.pubg.mixer.backend.repository.map;

import com.pubg.mixer.backend.entity.PubgMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<PubgMap, Long>, MapRepositoryCustom {

    boolean existsByMapNameInternalAndIdNot(String mapNameInternal, Long id);
}
