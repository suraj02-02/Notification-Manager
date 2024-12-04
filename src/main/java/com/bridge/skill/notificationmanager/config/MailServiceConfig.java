package com.bridge.skill.notificationmanager.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * <p>
 *    <code>MailServiceConfig</code> - Configuration for sending emails through SMTP configured.
 *    Any new email service configuration can be added here.
 * </p>
 *
 * @author surajyadav
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.email")
public class MailServiceConfig {

    private String host;
    private int port;
    private String username;
    // For now getting the value of SMTP password from system environment to avoid security conflicts
    private String password;

    @PostConstruct
    public void init() {
        this.password = System.getenv("EMAIL_PASSWORD");
    }

    /**
     * <code>JavaMailSender</code> bean for sending emails through SMTP configured.
     * @return <code>JavaMailSender</code>
     */
    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }



}
