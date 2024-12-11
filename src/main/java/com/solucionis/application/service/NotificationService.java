package com.solucionis.application.service;

import com.solucionis.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class NotificationService {

    private static final String NOTIFICATION_URL = "https://util.devi.tools/api/v1/notify";

    public void notifyTransaction(User user, BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForObject(NOTIFICATION_URL, createNotificationPayload(user, amount), Void.class);
        } catch (Exception e) {
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }

    private NotificationPayload createNotificationPayload(User user, BigDecimal amount) {
        return new NotificationPayload(user.getEmail(), "You received " + amount + " in your account!");
    }

    @SuppressWarnings("unused")
    private static class NotificationPayload {
        private final String to;
        private final String message;

        public NotificationPayload(String to, String message) {
            this.to = to;
            this.message = message;
        }

        public String getTo() {
            return to;
        }

        public String getMessage() {
            return message;
        }
    }
}
