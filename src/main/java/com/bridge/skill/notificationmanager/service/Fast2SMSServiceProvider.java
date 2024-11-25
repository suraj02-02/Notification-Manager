package com.bridge.skill.notificationmanager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Qualifier("smsNotifier")
public class Fast2SMSServiceProvider implements ISMSNotifier {

    private final WebClient webClient;
    private final String smsApiKey;

    public Fast2SMSServiceProvider(final WebClient.Builder webClient ,
                                   @Value("${fast2sms.api.key}") final String apiKey ,
                                   @Value("${fast2sms.base.uri}") final String baseUrl)
    {
        this.smsApiKey = apiKey;
        this.webClient = webClient.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<Void> notify(Object message) {
        String payload = "sender_id=FSTSMS&message=" + message + "&route=otp&numbers=9667555245";
        this.webClient.post()
                .uri("/bulkV2")
                .header("authorization" , smsApiKey)
                .body(BodyInserters.fromValue(message))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Error sending SMS");

        return null;
    }
}
