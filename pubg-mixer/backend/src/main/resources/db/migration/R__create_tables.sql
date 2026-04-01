-- ============================
-- MEMBER
-- ============================
CREATE TABLE IF NOT EXISTS member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    tier INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FULLTEXT INDEX ft_idx_nickname (nickname) WITH PARSER ngram
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================
-- TEAM (매칭 단위)
-- create_date 기준으로 이벤트 구분
-- ============================
CREATE TABLE IF NOT EXISTS team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_number INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_team_number_date (team_number, create_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================
-- TEAM_MEMBER
-- ============================
CREATE TABLE IF NOT EXISTS team_member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_id INT NOT NULL,
    member_id INT NOT NULL,
    CONSTRAINT fk_team_member_team
    FOREIGN KEY (team_id) REFERENCES team(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_team_member_member
    FOREIGN KEY (member_id) REFERENCES member(id)
    ON DELETE CASCADE,
    UNIQUE KEY uk_team_member (team_id, member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================
-- MAP
-- ============================
CREATE TABLE IF NOT EXISTS `map` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `map_name_internal` VARCHAR(50) NOT NULL UNIQUE COMMENT 'API 원본 이름 (예: Erangel_Main)',
    `map_name_display` VARCHAR(50) NOT NULL COMMENT '표기용 이름 (예: 에란겔)',
    `map_size_km` INT COMMENT '맵 크기 (8, 4, 2 등)',
    `image_path` VARCHAR(255) COMMENT '서버 내 이미지 파일 경로',
    `max_coordinate` FLOAT DEFAULT 816000.0 COMMENT '맵의 최대 좌표값 (좌표 변환용)',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================
-- LANDMARK
-- 맵별 랜드마크
-- ============================
CREATE TABLE IF NOT EXISTS `landmark` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `map_id` INT NOT NULL COMMENT '소속 맵 ID',
    `name` VARCHAR(100) NOT NULL COMMENT '지명 (예: Pochinki)',
    `x_coord` FLOAT NOT NULL COMMENT '중심 X 좌표',
    `y_coord` FLOAT NOT NULL COMMENT '중심 Y 좌표',
    `radius` FLOAT DEFAULT 5000.0 COMMENT '지역 판정 범위 (반지름)',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_map_id` FOREIGN KEY (`map_id`) REFERENCES `map` (`id`) ON DELETE CASCADE,
    UNIQUE KEY `uk_landmark_map_name` (`map_id`, `name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ============================
-- MATCH_HISTORY
-- 팀별 맵 랜드마크 배정 결과
-- ============================
CREATE TABLE IF NOT EXISTS match_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_id INT NOT NULL,
    map_id INT NOT NULL,
    landmark_id INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_history_team
    FOREIGN KEY (team_id) REFERENCES team(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_history_map
    FOREIGN KEY (map_id) REFERENCES map(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_history_landmark
    FOREIGN KEY (landmark_id) REFERENCES landmark(id)
    ON DELETE CASCADE,

    UNIQUE KEY uk_team_map (team_id, map_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
