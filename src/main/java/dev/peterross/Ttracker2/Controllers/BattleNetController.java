package dev.peterross.Ttracker2.Controllers;
import java.awt.PageAttributes;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import dev.peterross.Ttracker2.payload.BattleNetTokenResponse;

@RestController
@RequestMapping("/api/v1/battle-net")
@CrossOrigin(origins="http://localhost:3000")

public class BattleNetController {
    
    @Value("${battle.net.client-id}")
    private String clientId;

    @Value("${battle.net.client-secret}")
    private String clientSecret;


    @PostMapping("/token")
    public ResponseEntity<BattleNetTokenResponse> getBattleNetToken() {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId,clientSecret);

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<BattleNetTokenResponse> response = restTemplate.postForEntity(
            "https://oauth.battle.net/token",
            request,
            BattleNetTokenResponse.class
            );

        return response;
    }

}





