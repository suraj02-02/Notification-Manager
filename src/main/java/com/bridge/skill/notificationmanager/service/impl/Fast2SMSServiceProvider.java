package com.bridge.skill.notificationmanager.service.impl;

import com.bridge.skill.notificationmanager.model.BaseEventRequest;
import com.bridge.skill.notificationmanager.service.intf.ISMSNotifier;
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
    public Mono<Void> notify(final BaseEventRequest baseEventRequest) {

        return null;
    }
}
