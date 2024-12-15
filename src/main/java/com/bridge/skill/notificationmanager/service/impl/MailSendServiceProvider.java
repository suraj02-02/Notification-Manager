package com.bridge.skill.notificationmanager.service.impl;

import com.bridge.skill.notificationmanager.constants.EventType;
import com.bridge.skill.notificationmanager.entity.Event;
import com.bridge.skill.notificationmanager.model.BaseEventRequest;
import com.bridge.skill.notificationmanager.model.EmailEventRequest;
import com.bridge.skill.notificationmanager.repository.NotificationEventRepository;
import com.bridge.skill.notificationmanager.service.intf.IEmailNotifier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Qualifier("emailNotifier")
@AllArgsConstructor
public class MailSendServiceProvider implements IEmailNotifier {

    private final JavaMailSender javaMailSender;
    private final NotificationEventRepository notificationEventsRepository;

    @Override
    public Mono<Void> notify(final BaseEventRequest baseEventRequest) {

        return Mono.just(baseEventRequest)
                .map(EmailEventRequest.class::cast)
                .flatMap(emailEventRequest -> {
                    final SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setTo(emailEventRequest.getEmailTo());
                    return this.notificationEventsRepository.findEventByEventType(emailEventRequest.getEventType())
                            .map(event -> {
                                mailMessage.setSubject(event.getSubject());
                                mailMessage.setText(event.getMessagePayload());
                                return mailMessage;
                            })
                            .flatMap(mail -> Mono.fromRunnable(() -> javaMailSender.send(mail)));
                })
                .doOnError(error -> log.error("Error while sending email for user registration event : {}", error.getMessage()))
                .then();
    }
}
