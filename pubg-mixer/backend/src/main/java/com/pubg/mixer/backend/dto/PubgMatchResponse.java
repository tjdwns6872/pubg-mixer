package com.pubg.mixer.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class PubgMatchResponse {
    private MatchData data;
    private List<IncludedData> included; // 플레이어 통계 및 팀 정보가 섞여 있음

    @Getter
    @NoArgsConstructor
    public static class MatchData {
        private String id;
        private MatchAttributes attributes;
    }

    @Getter
    @NoArgsConstructor
    public static class MatchAttributes {
        private String mapName;   // 맵 (Erangel_Main 등)
        private String gameMode;  // 모드 (squad, duo 등)
        private String createdAt; // 매치 시간
        private int duration;     // 경기 시간(초)
    }

    // 'included' 배열 내의 다양한 객체를 구분하기 위한 설정
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Participant.class, name = "participant"),
            @JsonSubTypes.Type(value = Roster.class, name = "roster")
    })
    @Getter
    @NoArgsConstructor
    public abstract static class IncludedData {
        private String id;
    }

    // 개별 플레이어의 상세 성적
    @Getter
    @NoArgsConstructor
    public static class Participant extends IncludedData {
        private ParticipantAttributes attributes;

        @Getter
        @NoArgsConstructor
        public static class ParticipantAttributes {
            private Stats stats;

            @Getter
            @NoArgsConstructor
            public static class Stats {
                private String name;        // 플레이어 닉네임
                private int kills;          // 킬 수
                private int assists;        // 어시스트
                private double damageDealt; // 가한 데미지
                private int winPlace;       // 최종 순위
                private int deathType;      // 사망 원인 (alive, byplayer 등)
                private int longestKill;    // 최장 거리 킬
            }
        }
    }

    // 팀(스쿼드) 정보 (필요시 사용)
    @Getter
    @NoArgsConstructor
    public static class Roster extends IncludedData {
        // 팀 관련 정보 필드 추가 가능
    }
}
