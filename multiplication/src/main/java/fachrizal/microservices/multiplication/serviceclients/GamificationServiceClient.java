package fachrizal.microservices.multiplication.serviceclients;

import fachrizal.microservices.multiplication.challenge.ChallengeAttempt;
import fachrizal.microservices.multiplication.challenge.ChallengeSolvedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GamificationServiceClient {

    private final RestTemplate restTemplate;
    private final String gamificationHostUrl;
    public GamificationServiceClient(final RestTemplateBuilder builder,
                                     @Value("${service.gamification.host}")
                                     final String gamificationHostUrl) {
        restTemplate = builder.build();
        this.gamificationHostUrl = gamificationHostUrl;
    }

    public boolean sendAttempt(final ChallengeAttempt attempt) {
        try {
            ChallengeSolvedDTO dto = new ChallengeSolvedDTO(attempt.getId(),
                    attempt.isCorrect(), attempt.getFactorA(),
                    attempt.getFactorB(), attempt.getUser().getId(),
                    attempt.getUser().getAlias());
            /**
             * We don’t expect a response body, but the method’s signature
             * requires it, so we can set it to String.class, for example.
             */
            ResponseEntity<String> r = restTemplate.postForEntity(
                    gamificationHostUrl + "/attempts", dto,
                    String.class);
            log.info("Gamification service response: {}", r.getStatusCode());
            return r.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt.", e);
            return false;
        }
    }
}
