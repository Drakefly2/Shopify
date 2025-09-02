package com.drakefly.ReservationShopify.controller;

import com.drakefly.ReservationShopify.services.ShopifyService;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "testordersbooking.myshopify.com")
public class ShopifyController {

    private final ShopifyService shopifyService;

    // Spring injecte automatiquement l'instance de ShopifyService
    public ShopifyController(ShopifyService shopifyService) {
        this.shopifyService = shopifyService;
    }

    @GetMapping("/shop")
    public String getShopInfo() {
        Logger logger = Logger.getLogger(ShopifyController.class.getName());
        logger.info("getShopInfo a été reçu");
        try {
            JsonObject shopInfo = shopifyService.getShopInfo();
            // Retourne directement la réponse JSON
            return shopInfo.toString();
        } catch (IOException e) {
            return "{\"error\": \"Failed to retrieve shop info: " + e.getMessage() + "\"}";
        }
    }
}
