package com.bridge.skill.notificationmanager.service;

import com.bridge.skill.notificationmanager.model.UserRegistrationEventData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Qualifier("emailNotifier")
@AllArgsConstructor
public class MailSendServiceProvider implements IEmailNotifier {

    private final JavaMailSender javaMailSender;

    @Override
    public Mono<Void> notify(Object message) {

        return Mono.fromRunnable(() -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(((UserRegistrationEventData) message).getEmail());
            simpleMailMessage.setSubject("Bridge Skill Registration");
            simpleMailMessage.setText("Welcome to Bridge Skill");
            javaMailSender.send(simpleMailMessage);
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

}
