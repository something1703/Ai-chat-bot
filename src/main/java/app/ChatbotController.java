package app;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.ChatbotResponse;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }


    @PostMapping("/query")
    public ChatbotResponse queryChatbot(@RequestBody ChatbotRequest request) {
        ChatbotResponse response = new ChatbotResponse();
        String message = chatbotService.generateChatbotResponse(request);
        response.setMessage(message);
        return response;
    }
}