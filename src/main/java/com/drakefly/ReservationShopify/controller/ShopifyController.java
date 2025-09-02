package com.drakefly.ReservationShopify.controller;

import com.drakefly.ReservationShopify.services.ShopifyService;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ShopifyController {

    private final ShopifyService shopifyService;

    // Spring injecte automatiquement l'instance de ShopifyService
    public ShopifyController(ShopifyService shopifyService) {
        this.shopifyService = shopifyService;
    }

    @GetMapping("/shop")
    public String getShopInfo() {
        try {
            JsonObject shopInfo = shopifyService.getShopInfo();
            // Retourne directement la réponse JSON
            return shopInfo.toString();
        } catch (IOException e) {
            return "{\"error\": \"Failed to retrieve shop info: " + e.getMessage() + "\"}";
        }
    }
}
