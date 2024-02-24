package com.ouharri.aftas.model.dto.basic;

import com.ouharri.aftas.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Represents a basic DTO for chat notifications in the system.
 * This class includes information such as the ID, sender, recipient, and content of the chat notification.
 *
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotification {
    /**
     * The unique identifier for the chat notification.
     */
    private UUID id;

    /**
     * The user who sent the chat notification.
     */
    private User sender;

    /**
     * The user who is the recipient of the chat notification.
     */
    private User recipient;

    /**
     * The content of the chat notification.
     */
    private String content;
}