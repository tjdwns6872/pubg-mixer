package com.pubg.mixer.backend.external.dto.pubg;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PubgPlayerResponse {
    private List<PlayerData> data;

    @Getter
    @NoArgsConstructor
    public static class PlayerData {
        private String id; // account Id
        private Attributes attributes;
        private Relationships relationships;
    }

    @Getter
    @NoArgsConstructor
    public static class Attributes {
        private String name; // 닉네임
        private String shardId; // 플랫폼 (steam 등)
    }

    @Getter
    @NoArgsConstructor
    public static class Relationships {
        private MatchData matches;
    }

    @Getter
    @NoArgsConstructor
    public static class MatchData {
        private List<MatchReference> data;
    }

    @Getter
    @NoArgsConstructor
    public static class MatchReference {
        private String id; // 실제 매치 ID
        private String type;
    }
}
