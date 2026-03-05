-- ============================
-- MEMBER
-- ============================
CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    tier INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ============================
-- TEAM (매칭 단위)
-- create_date 기준으로 이벤트 구분
-- ============================
CREATE TABLE team (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_number INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_team_number_date (team_number, create_date)
);

-- ============================
-- TEAM_MEMBER
-- ============================
CREATE TABLE team_member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_team_member_team
        FOREIGN KEY (team_id) REFERENCES team(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_team_member_member
        FOREIGN KEY (member_id) REFERENCES member(id)
        ON DELETE CASCADE,
    UNIQUE KEY uk_team_member (team_id, member_id)
);

-- ============================
-- MAP
-- ============================
CREATE TABLE map (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- ============================
-- LANDMARK
-- 맵별 랜드마크
-- ============================
CREATE TABLE landmark (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    map_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT fk_landmark_map
        FOREIGN KEY (map_id) REFERENCES map(id)
        ON DELETE CASCADE,
    UNIQUE KEY uk_landmark_map_name (map_id, name)
);

-- ============================
-- MATCH_HISTORY
-- 팀별 맵 랜드마크 배정 결과
-- ============================
CREATE TABLE match_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_id BIGINT NOT NULL,
    map_id BIGINT NOT NULL,
    landmark_id BIGINT NOT NULL,
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
);