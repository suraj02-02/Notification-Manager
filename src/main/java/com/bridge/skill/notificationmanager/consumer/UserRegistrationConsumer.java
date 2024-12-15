package com.bridge.skill.notificationmanager.consumer;

import com.bridge.skill.notificationmanager.constants.EventType;
import com.bridge.skill.notificationmanager.model.EmailEventRequest;
import com.bridge.skill.notificationmanager.model.UserRegistrationEventRequest;
import com.bridge.skill.notificationmanager.service.intf.INotifier;
import com.bridge.skill.notificationmanager.util.MessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <code>UserRegistrationConsumer</code> - Kafka consumer for user registration events.
 * @author surajyadav
 */

@Slf4j
@Component
public class UserRegistrationConsumer implements BaseEventConsumer {

    private final INotifier emailNotifier;
    private final MessageConverter messageConverter;

    public UserRegistrationConsumer(@Qualifier("mailSendServiceProvider") final INotifier emailNotifier ,
                                    final MessageConverter messageConverter)
    {
        this.messageConverter = messageConverter;
        this.emailNotifier = emailNotifier;
    }

    @Override
    @KafkaListener(topics = "${cable.event-types.USER_REGISTRATION_EVENT}", groupId = "${spring.kafka.consumer.group-id}")
    public Mono<Void> consumeEvent(final Object message) {

        return Mono.just(message)
                .doOnNext(msg -> log.info("Message received from cable for user registration event"))
                .map(msg -> this.messageConverter.deserialize(((ConsumerRecord<? , ?>) msg).value(), UserRegistrationEventRequest.class))
                .doOnNext(eventReq -> log.info("Sending event to email Notifier for userId : {}", eventReq.getUserId()))
                .map(eventData -> new EmailEventRequest(eventData.getEmail(), EventType.USER_REGISTRATION_EVENT))
                .flatMap(this.emailNotifier::notify)
                .doOnError(error -> log.error("Error while sending email for user registration event : {}", error.getMessage()))
                .then();
    }

}
