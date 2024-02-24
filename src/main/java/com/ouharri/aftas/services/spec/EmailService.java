package com.ouharri.aftas.services.spec;

import jakarta.mail.MessagingException;

/**
 * Interface defining email-related services.
 */
public interface EmailService {

    /**
     * Sends a simple email message.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param text    The content of the email.
     */
    void sendSimpleMessage(String to, String subject, String text);

    /**
     * Sends an email message with an attachment.
     *
     * @param to               The recipient's email address.
     * @param subject          The subject of the email.
     * @param text             The content of the email.
     * @param pathToAttachment The file path to the attachment.
     * @throws MessagingException Thrown if there is an issue with creating or sending the email message.
     */
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;

    /**
     * Sends a simple email message asynchronously.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param text    The content of the email.
     */
    void sendSimpleMessageAsync(String to, String subject, String text);
}