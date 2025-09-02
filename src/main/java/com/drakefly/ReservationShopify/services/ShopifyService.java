package com.drakefly.ReservationShopify.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

@Service
public class ShopifyService {

    @Value("${shopify.domain}")
    private String shopifyDomain;

    @Value("${shopify.access-token}")
    private String accessToken;

    @Value("${shopify.api-version}")
    private String apiVersion;

    private final OkHttpClient client = new OkHttpClient();

    public JsonObject getShopInfo() throws IOException {
        String url = String.format("https://%s/admin/api/%s/shop.json", shopifyDomain, apiVersion);

        Request request = new Request.Builder()
                .url(url)
                .header("X-Shopify-Access-Token", accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return JsonParser.parseString(responseBody).getAsJsonObject();
            } else {
                throw new IOException("Erreur de requête à l'API Shopify : " + response.code() + " - " + response.message());
            }
        }
    }
}
