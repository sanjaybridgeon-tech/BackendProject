package application.demo.controller;

import application.demo.service.GeminiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ChatController {

    private final GeminiService geminiService;

    public ChatController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping
    public String chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        return geminiService.askAI(message);
    }
}