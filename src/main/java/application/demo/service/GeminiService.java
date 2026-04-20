package application.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String askAI(String message) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", message)
                                }
                        )
                }
        );

        try {
            Map response = restTemplate.postForObject(url, request, Map.class);


            Map candidate = ((java.util.List<Map>) response.get("candidates")).get(0);
            Map content = (Map) candidate.get("content");
            java.util.List<Map> parts = (java.util.List<Map>) content.get("parts");

            return parts.get(0).get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to AI";
        }
    }
}