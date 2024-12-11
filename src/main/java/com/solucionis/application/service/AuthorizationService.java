package com.solucionis.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationService {

    private static final String AUTHORIZATION_URL = "https://util.devi.tools/api/v2/authorize";

    public boolean isAuthorized() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(AUTHORIZATION_URL, String.class);
            return "authorized".equalsIgnoreCase(response);
        } catch (Exception e) {
            return false;
        }
    }
}
