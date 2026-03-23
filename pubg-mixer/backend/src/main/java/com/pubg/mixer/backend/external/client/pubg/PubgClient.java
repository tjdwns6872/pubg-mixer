package com.pubg.mixer.backend.external.client.pubg;

import com.pubg.mixer.backend.external.dto.pubg.PubgPlayerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "pubg-api",
        url = "${external.pubg.url}",
        configuration = PubgFeignConfig.class
)
public interface PubgClient {

    @GetMapping("/shards/{shard}/players")
    PubgPlayerResponse getPlayersByNickname(
            @PathVariable("shard") String shard,
            @RequestParam("filter[playerNames]") String nicknames
    );
}
