package com.codedifferently.groupone.SpyGlass.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService implements EmailSender{

    private Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try{
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(env.getProperty("spring.mail.host"));
            mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
            mailSender.setUsername(env.getProperty("spring.mail.username"));
            mailSender.setPassword(env.getProperty("spring.mail.password"));
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
            mailSender.setJavaMailProperties(props);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("SpyGlass: Confirm your email");
            helper.setFrom("Group1@codedifferently.stayready.com");
            mailSender.send(mimeMessage);
        } catch(MessagingException e){
            log.error("Failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
