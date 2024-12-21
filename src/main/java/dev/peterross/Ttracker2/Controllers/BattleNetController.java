package dev.peterross.Ttracker2.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dev.peterross.Ttracker2.payload.CharacterAvatarResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j  // This Lombok annotation automatically creates a logger

@RestController
@RequestMapping("/api/v1/battle-net")
@CrossOrigin(origins="http://localhost:3000")

public class BattleNetController {
    
    @Value("${battle.net.client-id}")
    private String clientId;

    @Value("${battle.net.client-secret}")
    private String clientSecret;


    // THE LOGIC HAS BEEN MOVED TO A FUNCTION ON GOOGLE CLOUD 
/* private static final Logger log = LoggerFactory.getLogger(BattleNetController.class);
   @PostMapping("/token")
public ResponseEntity<BattleNetTokenResponse> getBattleNetToken() {
    try {
        CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setResponseTimeout(Timeout.ofSeconds(10))
                .setConnectionRequestTimeout(Timeout.ofSeconds(10))
                .build())
            .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(20)
                .build())
            .build();

        HttpComponentsClientHttpRequestFactory requestFactory = 
            new HttpComponentsClientHttpRequestFactory(httpClient);
        
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);
        headers.set("Accept", "application/json");
        headers.set("User-Agent", "tTracker/1.0");
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        
        return restTemplate.postForEntity(
            "https://oauth.battle.net/token",
            request,
            BattleNetTokenResponse.class
        );
        
    } catch (HttpClientErrorException | HttpServerErrorException e) {
        log.error("Battle.net API error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
        return ResponseEntity.status(e.getStatusCode()).body(null);
    } catch (Exception e) {
        log.error("Unexpected error while getting Battle.net token", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
} */

    @GetMapping("/character-avatar")
    public ResponseEntity <String> getCharacterAvatar (
        @RequestParam String server,
        @RequestParam String characterName,
        @RequestParam String accessToken
    ) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Battlenet-Namespace", "profile-eu");

        HttpEntity<?> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CharacterAvatarResponse> response = restTemplate.exchange(
            "https://eu.api.blizzard.com/profile/wow/character/{server}/{characterName}/character-media",
        HttpMethod.GET,
        request,
        CharacterAvatarResponse.class,
        server.toLowerCase(),
        characterName.toLowerCase()
        );
        System.out.println(response);

        return ResponseEntity.ok(response.getBody().getMainRawUrl());
        



    }
        
    
    

}