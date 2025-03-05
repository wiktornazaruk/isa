package com.example.event;

import com.example.event.WarehouseEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WarehouseEventPublisher {
    private final RestTemplate restTemplate;
    private static final String PRODUCT_SERVICE_URL = "http://product-service:8092/api/warehouse-events";

    public WarehouseEventPublisher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyProductService(WarehouseEvent event) {
        restTemplate.postForEntity(PRODUCT_SERVICE_URL, event, Void.class);
    }

}