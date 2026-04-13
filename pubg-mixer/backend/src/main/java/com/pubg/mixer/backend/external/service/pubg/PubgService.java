package com.pubg.mixer.backend.external.service.pubg;

import com.pubg.mixer.backend.dto.PubgMatchResponse;
import com.pubg.mixer.backend.external.client.pubg.PubgClient;
import com.pubg.mixer.backend.external.dto.pubg.PubgPlayerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PubgService {

    private final PubgClient pubgClient;

    public void insertResult(String nickname, Integer round){
        List<String> getMatchList = getMatchIdsByNickname(nickname, round);

        List<PubgMatchResponse> matchDetails = new ArrayList<>();

        for(String matchId : getMatchList){
            PubgMatchResponse matchs = pubgClient.getMatchDataList(matchId);
            log.info("\n\n{}", matchs);
            matchDetails.add(matchs);
        }
    }

    public List<String> getMatchIdsByNickname(String nickname, Integer round) {
        // 1. API 호출
        PubgPlayerResponse response = pubgClient.getPlayersByNickname(nickname);

        // 2. 결과가 없거나 데이터가 비어있을 경우 처리
        if (response.getData() == null || response.getData().isEmpty()) {
            throw new RuntimeException("플레이어를 찾을 수 없습니다.");
        }

        // 3. 최근 매치 ID 리스트만 추출해서 반환
        return response.getData().get(0)
                .getRelationships()
                .getMatches()
                .getData()
                .stream()
                .limit(round)
                .map(PubgPlayerResponse.MatchReference::getId)
                .toList();
    }
}
