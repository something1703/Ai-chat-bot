package app;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@AllArgsConstructor
public class ChatbotService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public ChatbotService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateChatbotResponse(ChatbotRequest request) {
        String prompt = buildPrompt(request);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        String response = webClient
                .post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response);
            return json.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String buildPrompt(ChatbotRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append("You are NaviSafe, a Smart Tourist Safety Assistant.\n");
        sb.append("Tourist Information:\n");
        sb.append(" - Name: ").append(request.getTouristName()).append("\n");
        sb.append(" - Age: ").append(request.getAge()).append("\n");
        sb.append(" - Digital ID: ").append(request.getDigitalId()).append("\n");
        sb.append(" - Trip Duration: ").append(request.getTripStartDate())
                .append(" to ").append(request.getTripEndDate()).append("\n\n");

        sb.append("Current Location:\n");
        sb.append(" - Coordinates: ").append(request.getCurrentLocation().getLat())
                .append(", ").append(request.getCurrentLocation().getLng()).append("\n");
        sb.append(" - Address: ").append(request.getCurrentLocation().getAddress()).append("\n");
        sb.append(" - Zone Status: ").append(request.getCurrentLocation().getZoneStatus()).append("\n");
        sb.append(" - Safety Score: ").append(request.getCurrentLocation().getSafetyScore()).append("%\n\n");

        sb.append("Environment:\n");
        sb.append(" - Nearby Incidents: ").append(request.getEnvironment().getNearbyIncidents()).append("\n\n");

        sb.append("User Query:\n");
        sb.append(request.getUserQuery()).append("\n\n");

        sb.append("Instructions:\n");
        sb.append(" - Respond in a tourist-friendly tone.\n");
        sb.append(" - If in Safe Zone, reassure the user.\n");
        sb.append(" - If in Red or Restricted Zone, warn clearly and suggest action.\n");
        sb.append(" - If user asks general questions, act as a local guide.\n");

        return sb.toString();
    }
}
