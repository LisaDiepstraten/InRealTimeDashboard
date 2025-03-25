package fontys.project.idk.application.controller;

import fontys.project.idk.application.business.interfaces.BWEventService;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EventWebSocketController {

    private final BWEventService bwEventService;

    public EventWebSocketController(BWEventService bwEventService) {
        this.bwEventService = bwEventService;
    }

    @MessageMapping("/sendEvent") // Matches the frontend's publish destination
    @SendTo("/topic/event-logs-products") // Matches the frontend's subscription destination
    public BasWorldEvent sendEvent(BasWorldEvent event) {
        // Handle and broadcast the event
        BasWorldEvent savedEvent = bwEventService.createBWEvent(event);
        System.out.println("Received and saved WebSocket event: " + savedEvent);

        // Broadcast the saved event
        return savedEvent; // This will be sent to all subscribers of /topic/event-logs-products
    }
}
