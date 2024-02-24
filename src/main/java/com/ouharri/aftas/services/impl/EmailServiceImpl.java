package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.services.spec.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Service implementation for sending emails using Spring's JavaMailSender.
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private static final String FROM_ADDRESS = "noreply@aftas.com";
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * Sends a simple email message.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param text    The content of the email.
     */
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_ADDRESS);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    /**
     * Sends an email message with an attachment.
     *
     * @param to               The recipient's email address.
     * @param subject          The subject of the email.
     * @param text             The content of the email.
     * @param pathToAttachment The file path to the attachment.
     * @throws MessagingException Thrown if there is an issue with creating or sending the email message.
     */
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(FROM_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);
    }

    /**
     * Sends a simple email message asynchronously.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param text    The content of the email.
     */
    public void sendSimpleMessageAsync(String to, String subject, String text) {
        executorService.submit(() -> sendSimpleMessage(to, subject, text));
    }
}